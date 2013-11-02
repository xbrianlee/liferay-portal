/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.kernel.process;

import com.liferay.portal.kernel.util.NamedThreadFactory;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.io.IOException;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Shuyang Zhou
 */
public class ProcessUtil {

	public static final ConsumerOutputProcessor CONSUMER_OUTPUT_PROCESSOR =
		new ConsumerOutputProcessor();

	public static final LoggingOutputProcessor LOGGING_OUTPUT_PROCESSOR =
		new LoggingOutputProcessor();

	public static <O, E> Future<ObjectValuePair<O, E>> execute(
			OutputProcessor<O, E> outputProcessor, List<String> arguments)
		throws ProcessException {

		if (outputProcessor == null) {
			throw new NullPointerException("Output processor is null");
		}

		if (arguments == null) {
			throw new NullPointerException("Arguments is null");
		}

		ProcessBuilder processBuilder = new ProcessBuilder(arguments);

		try {
			Process process = processBuilder.start();

			ExecutorService executorService = _getExecutorService();

			try {
				Future<O> stdOutFuture = executorService.submit(
					new ProcessStdOutCallable<O>(outputProcessor, process));

				Future<E> stdErrFuture = executorService.submit(
					new ProcessStdErrCallable<E>(outputProcessor, process));

				return new BindedFuture<O, E>(
					stdOutFuture, stdErrFuture, process);
			}
			catch (RejectedExecutionException ree) {
				process.destroy();

				throw new ProcessException(
					"Cancelled execution because of a concurrent destroy", ree);
			}
		}
		catch (IOException ioe) {
			throw new ProcessException(ioe);
		}
	}

	public static <O, E> Future<ObjectValuePair<O, E>> execute(
			OutputProcessor<O, E> outputProcessor, String... arguments)
		throws ProcessException {

		return execute(outputProcessor, Arrays.asList(arguments));
	}

	public void destroy() {
		if (_executorService == null) {
			return;
		}

		synchronized (ProcessUtil.class) {
			if (_executorService != null) {
				_executorService.shutdownNow();

				_executorService = null;
			}
		}
	}

	private static ExecutorService _getExecutorService() {
		if (_executorService != null) {
			return _executorService;
		}

		synchronized (ProcessUtil.class) {
			if (_executorService == null) {
				_executorService = Executors.newCachedThreadPool(
					new NamedThreadFactory(
						ProcessUtil.class.getName(), Thread.MIN_PRIORITY,
						PortalClassLoaderUtil.getClassLoader()));
			}
		}

		return _executorService;
	}

	private static volatile ExecutorService _executorService;

	private static class BindedFuture<O, E>
		implements Future<ObjectValuePair<O, E>> {

		public BindedFuture(
			Future<O> stdOutFuture, Future<E> stdErrFuture, Process process) {

			_stdOutFuture = stdOutFuture;
			_stdErrFuture = stdErrFuture;
			_process = process;
		}

		@Override
		public boolean cancel(boolean mayInterruptIfRunning) {
			if (_stdOutFuture.isCancelled() || _stdOutFuture.isDone()) {
				return false;
			}

			_stdErrFuture.cancel(true);
			_stdOutFuture.cancel(true);
			_process.destroy();

			return true;
		}

		@Override
		public ObjectValuePair<O, E> get()
			throws ExecutionException, InterruptedException {

			E stdErrResult = _stdErrFuture.get();
			O stdOutResult = _stdOutFuture.get();

			return new ObjectValuePair<O, E>(stdOutResult, stdErrResult);
		}

		@Override
		public ObjectValuePair<O, E> get(long timeout, TimeUnit unit)
			throws ExecutionException, InterruptedException, TimeoutException {

			long startTime = System.currentTimeMillis();

			E stdErrResult = _stdErrFuture.get(timeout, unit);

			long elapseTime = System.currentTimeMillis() - startTime;

			long secondTimeout =
				timeout - unit.convert(elapseTime, TimeUnit.MILLISECONDS);

			O stdOutResult = _stdOutFuture.get(secondTimeout, unit);

			return new ObjectValuePair<O, E>(stdOutResult, stdErrResult);
		}

		@Override
		public boolean isCancelled() {
			return _stdOutFuture.isCancelled();
		}

		@Override
		public boolean isDone() {
			return _stdOutFuture.isDone();
		}

		private final Future<E> _stdErrFuture;
		private final Future<O> _stdOutFuture;
		private final Process _process;

	}

	private static class ProcessStdErrCallable<T> implements Callable<T> {

		public ProcessStdErrCallable(
			OutputProcessor<?, T> outputProcessor, Process process) {

			_outputProcessor = outputProcessor;
			_process = process;
		}

		@Override
		public T call() throws Exception {
			return _outputProcessor.processStdErr(_process.getErrorStream());
		}

		private final OutputProcessor<?, T> _outputProcessor;
		private final Process _process;

	}

	private static class ProcessStdOutCallable<T> implements Callable<T> {

		public ProcessStdOutCallable(
			OutputProcessor<T, ?> outputProcessor, Process process) {

			_outputProcessor = outputProcessor;
			_process = process;
		}

		@Override
		public T call() throws Exception {
			try {
				return _outputProcessor.processStdOut(
					_process.getInputStream());
			}
			finally {
				try {
					int exitCode = _process.waitFor();

					if (exitCode != 0) {
						throw new ProcessException(
							"Subprocess terminated with exit code " + exitCode);
					}
				}
				catch (InterruptedException ie) {
					_process.destroy();

					throw new ProcessException(
						"Forcibly killed subprocess on interruption", ie);
				}
			}
		}

		private final OutputProcessor<T, ?> _outputProcessor;
		private final Process _process;

	}

}
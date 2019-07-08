import React from 'react';
import Pagination from 'components/add_result/Pagination';
import {cleanup, fireEvent, render} from '@testing-library/react';

describe('Pagination', () => {
	it('has a disabled previous arrow on first page', () => {
		const {container} = render(
			<Pagination href='' onChange={jest.fn()} page={1} total={7} />
		);

		expect(container.querySelector('.page-item-previous')).toHaveClass(
			'disabled'
		);
	});

	it('has a disabled next arrow on last page', () => {
		const {container} = render(
			<Pagination href='' onChange={jest.fn()} page={7} total={7} />
		);

		expect(container.querySelector('.page-item-next')).toHaveClass(
			'disabled'
		);
	});

	it('shows the pagination dropdown menu when clicked on ellipses', () => {
		const {container, getByText} = render(
			<Pagination href='' onChange={jest.fn()} page={1} total={5} />
		);

		fireEvent.click(getByText('...'));

		expect(container.querySelector('.dropdown-menu')).toHaveClass('show');
	});

	it('has two ellipses buttons when on certain pages', () => {
		const {queryAllByText} = render(
			<Pagination href='' onChange={jest.fn()} page={5} total={10} />
		);

		expect(queryAllByText('...').length).toEqual(2);
	});

	it('shows no ellipses button when on certain pages', () => {
		const {queryByText} = render(
			<Pagination href='' onChange={jest.fn()} page={3} total={5} />
		);

		expect(queryByText('...')).toBeNull();
	});

	it('calls function onChange when new page is clicked on', () => {
		const onChange = jest.fn();

		const {getByText} = render(
			<Pagination href='' onChange={onChange} page={3} total={5} />
		);

		fireEvent.click(getByText('2'));

		expect(onChange.mock.calls.length).toBe(1);
	});
});

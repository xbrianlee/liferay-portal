import ClayButton from '@clayui/button';
import PropTypes from 'prop-types';
import React, {Component} from 'react';

class PageToolbar extends Component {
	static props = {
		onCancel: PropTypes.string,
		onPublish: PropTypes.func,
		onSaveAsDraft: PropTypes.func,
		submitDisabled: PropTypes.bool
	};

	static defaultProps = {
		submitDisabled: false
	};

	render() {
		const {onCancel, onPublish, onSaveAsDraft, submitDisabled} = this.props;

		return (
			<nav className='page-toolbar-root tbar upper-tbar'>
				<div className='container-fluid container-fluid-max-xl'>
					<ul className='tbar-nav'>
						<li className='tbar-item tbar-item-expand' />

						<li className='tbar-item'>
							<ClayButton
								className='btn-outline-borderless'
								displayType='secondary'
								href={onCancel}
								small
							>
								{Liferay.Language.get('cancel')}
							</ClayButton>
						</li>

						{onSaveAsDraft && (
							<li className='tbar-item'>
								<ClayButton
									displayType='secondary'
									onClick={onSaveAsDraft}
									small
								>
									{Liferay.Language.get('save-as-draft')}
								</ClayButton>
							</li>
						)}

						{onPublish && (
							<li className='tbar-item'>
								<ClayButton
									disabled={submitDisabled}
									onClick={onPublish}
									small
									type='submit'
								>
									{Liferay.Language.get('publish')}
								</ClayButton>
							</li>
						)}
					</ul>
				</div>
			</nav>
		);
	}
}

export default PageToolbar;

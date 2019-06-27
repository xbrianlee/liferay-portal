import ClayButton from '@clayui/button';
import ClayIcon from '@clayui/icon';
import PropTypes from 'prop-types';
import React, {Component} from 'react';

class Tag extends Component {
	static propTypes = {
		label: PropTypes.string,
		onClickDelete: PropTypes.func
	};

	state = {
		label: this.props.label
	};

	_handleDelete = event => {
		event.preventDefault();

		this.props.onClickDelete(this.props.label);
	};

	render() {
		const {label} = this.props;

		return (
			<span className='label label-dismissible label-lg label-secondary'>
				<span className='label-item label-item-expand'>{label}</span>

				<span className='label-item label-item-after'>
					<ClayButton
						aria-label={Liferay.Language.get('close')}
						className='close'
						displayType='secondary'
						onClick={this._handleDelete}
					>
						<ClayIcon symbol='times' />
					</ClayButton>
				</span>
			</span>
		);
	}
}

export default Tag;

import ClayMultiselect from 'components/shared/ClayMultiselect';
import React from 'react';
import {cleanup, render} from '@testing-library/react';

describe('ClayMultiselect', () => {
	it('renders', () => {
		const {container} = render(<ClayMultiselect />);

		expect(container.firstChild).not.toBeNull();
	});
});

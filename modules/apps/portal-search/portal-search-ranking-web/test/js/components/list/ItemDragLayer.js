import ItemDragLayer from 'components/list/ItemDragLayer';
import React from 'react';
import {cleanup, render} from '@testing-library/react';

describe('ItemDragLayer', () => {
	it('renders when dragging', () => {
		const {container} = render(
			<ItemDragLayer.DecoratedComponent dragging />
		);

		expect(container.firstChild).not.toBeNull();
		expect(container.firstChild).toBeVisible();
	});

	it('does not render by default', () => {
		const {container} = render(<ItemDragLayer.DecoratedComponent />);

		expect(container.firstChild).toBeNull();
	});
});

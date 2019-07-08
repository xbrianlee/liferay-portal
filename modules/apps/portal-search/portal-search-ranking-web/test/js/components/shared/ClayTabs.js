import {
	ClayTab,
	ClayTabList,
	ClayTabPanel,
	ClayTabs
} from 'components/shared/ClayTabs';
import React from 'react';
import {cleanup, render} from '@testing-library/react';

describe('ClayTabs', () => {
	it('renders', () => {
		const {asFragment} = render(
			<ClayTabs>
				<ClayTabList className='results-ranking-tabs'>
					<ClayTab>Tab 1</ClayTab>

					<ClayTab>Tab 2</ClayTab>
				</ClayTabList>

				<ClayTabPanel>Tab Panel 1</ClayTabPanel>
				<ClayTabPanel>Tab Panel 2</ClayTabPanel>
			</ClayTabs>
		);

		expect(asFragment()).toMatchSnapshot();
	});
});

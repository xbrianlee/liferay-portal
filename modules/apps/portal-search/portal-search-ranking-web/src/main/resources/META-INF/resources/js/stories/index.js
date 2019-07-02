import React from 'react';
import {action} from '@storybook/addon-actions';
import {addDecorator, storiesOf} from '@storybook/react';
import {array, boolean, select, text, withKnobs} from '@storybook/addon-knobs';
import {withA11y} from '@storybook/addon-a11y';

import Alias from 'components/alias/Alias';
import ClayEmptyState from 'components/shared/ClayEmptyState';
import FilterDisplay from '../components/list/FilterDisplay';
import FilterInput from 'components/list/FilterInput';
import Item from 'components/list/Item';
import ItemDragPreview from 'components/list/ItemDragPreview';
import List from 'components/list/List';
import PageToolbar from 'components/PageToolbar';
import ResultsRankingForm from 'components/ResultsRankingForm';
import ThemeContext from 'ThemeContext';
import {ClayIconSpriteContext} from '@clayui/icon';
import {mockDataMap} from 'stories/mock-data';

import '../../css/main.scss';

addDecorator(withA11y);
addDecorator(withKnobs);

addDecorator(storyFn => {
	const context = {
		constants: {
			WORKFLOW_ACTION_PUBLISH: '1',
			WORKFLOW_ACTION_SAVE_DRAFT: '2'
		},
		namespace:
			'_com_liferay_portal_search_ranking_web_portlet_ResultsRankingPortlet_',
		spritemap: '/o/admin-theme/images/lexicon/icons.svg'
	};

	return (
		<ClayIconSpriteContext.Provider value={context.spritemap}>
			<ThemeContext.Provider value={context}>
				<div className='results-rankings-root'>{storyFn()}</div>
			</ThemeContext.Provider>
		</ClayIconSpriteContext.Provider>
	);
});

const withSheet = storyFn => (
	<div className='sheet sheet-lg' style={{marginTop: '24px'}}>
		{storyFn()}
	</div>
);

storiesOf('Main|ResultsRankingForm', module)
	.add('default', () => (
		<ResultsRankingForm
			cancelUrl=''
			fetchDocumentsHiddenUrl=''
			fetchDocumentsUrl=''
			formName='testFm'
			saveActionUrl='#'
			searchTerm={text('Search Term', 'example')}
		/>
	))
	.add('with mock api', () => (
		<ResultsRankingForm
			cancelUrl=''
			fetchDocumentsHiddenUrl='http://www.mocky.io/v2/5cd31439310000e29a339bbd'
			fetchDocumentsUrl='http://www.mocky.io/v2/5cca1d49310000bf0312ce66'
			formName='testFm'
			initialAliases={['one', 'two', 'three']}
			saveActionUrl='#'
			searchTerm={text('Search Term', 'example')}
		/>
	));

storiesOf('Components|PageToolbar', module).add('default', () => (
	<PageToolbar submitDisabled={boolean('Disabled', false)} />
));

storiesOf('Components|Alias', module)
	.addDecorator(withSheet)
	.add('default', () => (
		<Alias
			keywords={array('Keywords', [], ',')}
			onClickDelete={action('onClickDelete')}
			onClickSubmit={action('onClickSubmit')}
		/>
	));

storiesOf('Components|List', module)
	.addDecorator(withSheet)
	.add('default', () => (
		<List
			dataLoading={false}
			dataMap={mockDataMap}
			fetchDocumentsUrl=''
			onAddResultSubmit={action('onAddResultSubmit')}
			onClickHide={action('onClickHide')}
			onClickPin={action('onClickPin')}
			onMove={action('onMove')}
			resultIds={['1', '2', '3', '4', '5']}
		/>
	))
	.add('empty', () => (
		<List
			dataLoading={false}
			dataMap={{}}
			fetchDocumentsUrl=''
			onAddResultSubmit={action('onAddResultSubmit')}
		/>
	))
	.add('error', () => (
		<List
			dataLoading={false}
			dataMap={{}}
			displayError
			fetchDocumentsUrl=''
			onAddResultSubmit={action('onAddResultSubmit')}
			onLoadResults={action('load-results')}
		/>
	))
	.add('item', () => (
		<div className='list-group'>
			<Item.DecoratedComponent {...mockDataMap['1']} />

			<Item.DecoratedComponent
				{...mockDataMap['1']}
				hidden
				pinned={false}
			/>

			<Item.DecoratedComponent />

			<Item.DecoratedComponent
				date='Apr 18 2018, 11:04 AM'
				title='Item with date only'
			/>

			<Item.DecoratedComponent
				author='Test Test'
				date='Apr 18 2018, 11:04 AM'
				title='Item with date and title'
			/>

			<Item.DecoratedComponent
				clicks='100'
				title='Item with title and clicks'
			/>
		</div>
	))
	.add('item drag preview', () => <ItemDragPreview {...mockDataMap['1']} />);

storiesOf('Components|EmptyState', module)
	.addDecorator(withSheet)
	.add('default', () => (
		<ClayEmptyState
			description={text('Description')}
			displayState={select(
				'Display State',
				{
					Empty: 'empty',
					Search: 'search',
					Success: 'success'
				},
				'search'
			)}
			title={text('Title')}
		/>
	))
	.add('with action', () => (
		<ClayEmptyState
			actionLabel='Refresh'
			description={text('Description')}
			displayState='empty'
			onClickAction={action('onClickAction')}
			title={text('Title')}
		/>
	));

storiesOf('Components|SearchBar', module)
	.addDecorator(withSheet)
	.add('input', () => (
		<FilterInput disableSearch={false} searchBarTerm='example' />
	))
	.add('display', () => (
		<FilterDisplay searchBarTerm='example' totalResultsCount={100} />
	));

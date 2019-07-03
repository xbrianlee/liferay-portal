import React from 'react';
import ReactDOM from 'react-dom';
import ResultsRankingForm from './components/ResultsRankingForm';
import ThemeContext from './ThemeContext';
import {ClayIconSpriteContext} from '@clayui/icon';

export default function(id, props, context) {
	ReactDOM.render(
		<ClayIconSpriteContext.Provider value={context.spritemap}>
			<ThemeContext.Provider value={context}>
				<div className='results-rankings-root'>
					<ResultsRankingForm {...props} />
				</div>
			</ThemeContext.Provider>
		</ClayIconSpriteContext.Provider>,
		document.getElementById(id)
	);
}

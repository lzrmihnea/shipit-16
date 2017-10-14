import { Component } from '@angular/core';
import { OnboardingPage } from '../onboarding/onboarding';

@Component({
	selector: 'page-start',
	templateUrl: 'start.html'
})
export class StartPage {

	onboarding: any;

	constructor() {
		this.onboarding = OnboardingPage;
	}
}

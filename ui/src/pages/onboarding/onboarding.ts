import { Component } from '@angular/core';
import { ViewChild } from '@angular/core';
import { Slides } from 'ionic-angular';

@Component({
	selector: 'onboarding',
	templateUrl: 'onboarding.html'
})
export class OnboardingPage {
	constructor() {

	}
	@ViewChild(Slides) slides: Slides;

	items = [{
		name: "Bananas",
		new: false
	},
	{
		name: "Oranges",
		new: false
	},
	{
		name: "Curpapier",
		new: false
	}]

	addItem() {
		this.items.push({
			name: "",
			new: true
		});
	}

	goToSlide(slide) {
		this.slides.slideTo(slide, 500);
	}

}

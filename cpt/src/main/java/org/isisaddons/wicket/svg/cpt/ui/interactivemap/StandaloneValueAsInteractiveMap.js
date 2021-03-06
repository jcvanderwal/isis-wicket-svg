/*
 *  Copyright 2013 Dan Haywood
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

$(function() {
	/**
	 * Sets the stroke(-width) attributes to SVG elements
	 */
	var strokeHandler = function (stroke, width) {
		return function() {
			var $this = $(this);
			var ref = $this.data('ref-class');
			$('.' + ref).css(
			{
				'stroke-width': width,
				'stroke': stroke
			}
			);
		}
	};


	$('.standaloneValueAsInteractiveMap .legendItem')
		.mouseenter(strokeHandler('black', '5'))
		.mouseleave(strokeHandler('', '0'));

	$('.standaloneValueAsInteractiveMap .link')
		.mouseenter(function() {
			var $this = $(this);
			var ref = $this.data('ref-id');
			$('#' + ref).css(
			{
				'stroke-width': '5',
				'stroke': 'black'
			}
			);
		})
		.mouseleave(function() {
			var $this = $(this);
			var ref = $this.data('ref-id');
			$('#' + ref).css(
			{
				'stroke-width': '0',
				'stroke': ''
			}
			);
		})
		.click(function() {
			var $this = $(this);
			var ref = $this.data('ref-id');
			$('#' + ref).click();
	});
});

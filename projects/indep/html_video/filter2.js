document.addEventListener('DOMContentLoaded', function() {
	var v = document.getElementById('v');
	var canvas = document.getElementById('c');
	var context = canvas.getContext('2d');
	var back = document.createElement('canvas');
	var backcontext = back.getContext('2d');

	var cw, ch;

	v.addEventListener('play', function() {
		cw = v.clientWidth;
		ch = v.clientHeight;
		canvas.width = cw;
		canvas.height = ch;
		back.width = cw;
		back.height = ch;
		draw(v, context, backcontext, cw, ch);
	}, false);

}, false);

function draw(v, c, bc, w, h) {
	if (v.paused || v.ended)
		return false;
	// First, draw it into the backing canvas
	bc.drawImage(v, 0, 0, w, h);
	// Grab the pixel data from the backing canvas
	var idata = bc.getImageData(0, 0, w, h);
	var data = idata.data;
	// Loop through the pixels, turning them grayscale
	var len = data.length;

	var parts = 2;
	var part_size = len / ((parts) * (parts));
	var part_array = new Array(parts);

	var cur_part = new Array(part_size);

	var n = parts * parts;
	// var lists = _.groupBy(data, function(a, b) {
	// return Math.floor(b / n);
	// });
	// lists = _.toArray(lists);
	// //Added this to convert the returned object to an array.
	//
	// temp = lists[0];
	// lists[0] = lists[1];
	// lists[1] = temp;

	//data = lists;
	function swap(arr, cuts, x1, y1, x2, y2) {
		var w = document.getElementById('c').width;
		var h = document.getElementById('c').height;
		var xdims = w / cuts;
		var ydims = h / cuts;
		for (var i = 0; i < 200; i += 1) {

			for (var j = 0; j < 200; j += 1) {

				for (var k = 0; k < 3; k += 1) {

					var cur = data[4 * (i + x1 * xdims) + (j + y1 * ydims) * w + k];
					var other = data[4 * (i + x2 * xdims) + (j + y2 * ydims) * w + k];
					var temp = cur;
					data[4 * (i + x1 * xdims) + (j + y1 * ydims) * w + k] = other;
					data[4 * (i + x2 * xdims) + (j + y2 * ydims) * w + k] = temp;

				}
			}
		}

	}

	data = swap(data, 3, 1, 1, 2, 2);

	idata.data = data;
	// Draw the pixels onto the visible canvas
	c.putImageData(idata, 0, 0);
	// Start over!
	setTimeout(function() {
		draw(v, c, bc, w, h);
	}, 0);
}
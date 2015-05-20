'use strict';

var config        = require('../config');
var gulp          = require('gulp');

gulp.task('watch', function() {

  // Scripts are automatically watched and rebundled by Watchify inside Browserify task
  gulp.watch(config.styles.src,  ['styles']);
  gulp.watch(config.images.src,  ['images']);
  gulp.watch(config.fonts.src,   ['fonts']);

});
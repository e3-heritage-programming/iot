'use strict';

module.exports = {

  'styles': {
    'src' : 'app/assets/sass/app.scss',
    'dest': 'public/assets/build/css'
  },

  'images': {
    'src' : 'app/assets/images/**/*',
    'dest': 'public/assets/build/images'
  },

  'fonts': {
    'src' : ['app/assets/fonts/**/*'],
    'dest': 'public/assets/build/fonts'
  },

  'gzip': {
    'src': 'build/**/*.{html,xml,json,css,js,js.map}',
    'dest': 'public/assets/build/',
    'options': {}
  },

  'dist': {
    'root'  : 'public/assets'
  },

  'browserify': {
    'entries'   : ['./app/assets/javascript/app.js'],
    'bundleName': 'app.js',
    'dest': 'public/assets/build/scripts',
    'sourcemap' : true
  }

};

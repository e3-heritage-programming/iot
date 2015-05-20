'use strict';

module.exports = {

    'styles': {
        'src': 'assets/sass/app.scss',
        'dest': 'public/build/css'
    },

    'images': {
        'src': 'assets/images/**/*',
        'dest': 'public/build/images'
    },

    'fonts': {
        'src': 'assets/fonts/**/*',
        'dest': 'public/build/fonts'
    },

    'gzip': {
        'src': 'build/**/*.{html,xml,json,css,js,js.map}',
        'dest': 'public/build/',
        'options': {}
    },

    'dist': {
        'root': 'public/assets'
    },

    'scripts': {
        'src': 'assets/javascript/**/*.js',
        'dest': 'public/build/scripts'
    },

    'browserify': {
        'entries': ['./assets/javascript/app.js'],
        'bundleName': 'app.js',
        'dest': 'public/build/scripts',
        'sourcemap': true
    }

}
;

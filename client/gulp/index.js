'use strict';

var fs = require('fs');
var onlyScripts = require('./util/scriptFilter');
var tasks = fs.readdirSync('./gulp/tasks/').filter(onlyScripts);

tasks.forEach(function (task) {
    console.log("GETTING: " + task);
    require('./tasks/' + task);
});
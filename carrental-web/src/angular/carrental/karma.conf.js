// Karma configuration file, see link for more information
// https://karma-runner.github.io/0.13/config/configuration-file.html

module.exports = function (config) {
  config.set({
    basePath: '',
    frameworks: ['jasmine', '@angular-devkit/build-angular'],
    plugins: [
      require('karma-jasmine'),
      require('karma-chrome-launcher'),
      require('karma-jasmine-html-reporter'),
      require('karma-coverage'),
      require('@angular-devkit/build-angular/plugins/karma'),
      require('karma-junit-reporter')
//      require('karma-phantomjs-launcher')
    ],
    client:{
      clearContext: false // leave Jasmine Spec Runner output visible in browser
    },
    coverageIstanbulReporter: {
      reports: [ 'html', 'lcovonly' ],
      fixWebpackSourcePaths: true
    },
    angularCli: {      
      environment: 'dev'
    },
//  reporters: ['progress', 'kjhtml', 'junit'],
    reporters: ['progress', 'kjhtml'],
    junitReporter: {
    	outputDir: 'reports', // results will be saved as $outputDir/$browserName.xml
        outputFile: 'junit.xml', // if included, results will be saved as $outputDir/$browserName/$outputFile
        useBrowserName: false // add browser name to report and classes names
          },
    port: 9876,
    colors: true,
    logLevel: config.LOG_INFO,
    autoWatch: true,
    browsers: ['Chromium', 'ChromeHeadless', 'ChromiumHeadless'],
    customLaunchers: {
        ChromeHeadless: {
          base: 'Chrome',
          flags: ['--no-sandbox','--headless', '--disable-gpu', '--remote-debugging-port=9222']
        },
        ChromiumHeadless: {
            base: 'Chromium',
            flags: ['--no-sandbox','--headless', '--disable-gpu', '--remote-debugging-port=9222']
          }
    },
    singleRun: false
  });
};

var gulp = require('gulp');
var concat = require('gulp-concat');
var mainBowerFiles = require('gulp-main-bower-files');

//Tarefa para copiar as fonts do font-awesome para pasta dist/libs/fonts 
gulp.task('libs-fonts', function () {
    gulp.src('./bower_components/components-font-awesome/fonts/**/*')
        .pipe(gulp.dest('dist/libs/fonts'));
});

//Tarefa para copiar os arquivos .css para pasta dist/libs/css  em um unico arquivo e minificar o codigo
gulp.task('libs-css', function () {
    return gulp.src('./bower.json')
        .pipe(mainBowerFiles(("**/*.css"), {
            overrides: {
                bootstrap: {
                    main: [
                        './dist/css/bootstrap.min.css'
                    ]
                }
            }
        }))
        .pipe(concat('myApp-libs.css'))
        .pipe(gulp.dest('dist/libs/css'))
});


//Tarefa para copiar os arquivos .js para pasta dist/libs/js  em um unico arquivo e minificar o codigo
gulp.task('libs-js', function () {
    return gulp.src('./bower.json')
        .pipe(mainBowerFiles(("**/*.js"), {
            overrides: {
                bootstrap: {
                    main: [
                        './assets/js/vendor/popper.min.js',
                        './dist/js/bootstrap.min.js'
                    ]
                },
                sweetalert: {
                    main:
                        [
                            './docs/assets/sweetalert/sweetalert.min.js'
                        ]
                }
            }
        }))
        .pipe(concat('myApp-libs.js'))
        .pipe(gulp.dest('dist/libs/js'))
});

// Tarefa padrão quando executado o comando GULP
gulp.task('default', ["libs-css", "libs-js", "libs-fonts"]);





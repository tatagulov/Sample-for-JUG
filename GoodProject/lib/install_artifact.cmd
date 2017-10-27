call mvn install:install-file -Dfile=jsqb-core-0.0.1.jar -DgroupId=io.github.tatagulov -DartifactId=jsqb-core -Dversion=0.0.1 -Dpackaging=jar -DgeneratePom=true
call mvn install:install-file -Dfile=jsqb-postgres-0.0.1.jar -DgroupId=io.github.tatagulov -DartifactId=jsqb-postgres -Dversion=0.0.1 -Dpackaging=jar -DgeneratePom=true
call mvn install:install-file -Dfile=jsqb-generator-0.0.1.jar -DgroupId=io.github.tatagulov -DartifactId=jsqb-generator -Dversion=0.0.1 -Dpackaging=jar -DgeneratePom=false
call mvn install:install-file -Dfile=jsqb-generator-0.0.1.pom -DgroupId=io.github.tatagulov -DartifactId=jsqb-generator -Dversion=0.0.1 -Dpackaging=pom
call mvn install:install-file -Dfile=JavaSmartQueryBuilder-0.0.1.pom -DgroupId=io.github.tatagulov -DartifactId=JavaSmartQueryBuilder -Dversion=0.0.1 -Dpackaging=pom

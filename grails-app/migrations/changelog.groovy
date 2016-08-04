//Core schema changes should go in schema-mysql-groovy
//For other changes, new xxx.groovy can be created, and included here.

databaseChangeLog = {
    include(file: 'schema-mysql.groovy')
}
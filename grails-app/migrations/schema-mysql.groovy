databaseChangeLog = {

    changeSet(author: "sudhir (generated)", id: "1470334381828-1") {
        createTable(tableName: "lapsi_block") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "lapsi_blockPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "content", type: "CLOB") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "VARCHAR(255)")

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "subject", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "sudhir (generated)", id: "1470334381828-2") {
        createTable(tableName: "lapsi_page") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "lapsi_pagePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "content", type: "CLOB")

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "draft", type: "VARCHAR(255)")

            column(name: "last_updated", type: "datetime")

            column(name: "meta_description", type: "VARCHAR(255)")

            column(name: "meta_keywords", type: "VARCHAR(255)")

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "parent_id", type: "BIGINT")

            column(name: "published", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "space_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "template", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "uri", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sudhir (generated)", id: "1470334381828-3") {
        createTable(tableName: "lapsi_space") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "lapsi_spacePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "uri", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sudhir (generated)", id: "1470334381828-4") {
        createTable(tableName: "role") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "rolePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sudhir (generated)", id: "1470334381828-5") {
        createTable(tableName: "tenant") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "tenantPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "domain", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "enabled", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "notes", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sudhir (generated)", id: "1470334381828-6") {
        createTable(tableName: "user_role") {
            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "role_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sudhir (generated)", id: "1470334381828-7") {
        createTable(tableName: "users") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "usersPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "account_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "account_locked", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "email", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "enabled", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "password", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "password_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "username", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sudhir (generated)", id: "1470334381828-8") {
        addPrimaryKey(columnNames: "user_id, role_id", constraintName: "user_rolePK", tableName: "user_role")
    }

    changeSet(author: "sudhir (generated)", id: "1470334381828-9") {
        addUniqueConstraint(columnNames: "space_id, uri", tableName: "lapsi_page")
    }

    changeSet(author: "sudhir (generated)", id: "1470334381828-10") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_LAPSI_BLOCKNAME_COL", tableName: "lapsi_block")
    }

    changeSet(author: "sudhir (generated)", id: "1470334381828-11") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_LAPSI_SPACENAME_COL", tableName: "lapsi_space")
    }

    changeSet(author: "sudhir (generated)", id: "1470334381828-12") {
        addUniqueConstraint(columnNames: "uri", constraintName: "UC_LAPSI_SPACEURI_COL", tableName: "lapsi_space")
    }

    changeSet(author: "sudhir (generated)", id: "1470334381828-13") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_ROLENAME_COL", tableName: "role")
    }

    changeSet(author: "sudhir (generated)", id: "1470334381828-14") {
        addUniqueConstraint(columnNames: "username", constraintName: "UC_USERSUSERNAME_COL", tableName: "users")
    }

    changeSet(author: "sudhir (generated)", id: "1470334381828-15") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK_apcc8lxk2xnug8377fatvbn04", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "users")
    }

    changeSet(author: "sudhir (generated)", id: "1470334381828-16") {
        addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "lapsi_page", constraintName: "FK_im6l9nl1roc9yqth4ms9vsmfr", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "lapsi_page")
    }

    changeSet(author: "sudhir (generated)", id: "1470334381828-17") {
        addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK_it77eq964jhfqtu54081ebtio", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role")
    }

    changeSet(author: "sudhir (generated)", id: "1470334381828-18") {
        addForeignKeyConstraint(baseColumnNames: "space_id", baseTableName: "lapsi_page", constraintName: "FK_ljlqp4wyf57ut3kwk1daiadek", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "lapsi_space")
    }
}

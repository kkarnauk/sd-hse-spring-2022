package ru.hse.sd.repo

import org.jetbrains.exposed.sql.transactions.transaction

@Suppress("SqlNoDataSourceInspection", "SqlResolve")
internal fun initializeDb() {
    transaction {
        exec(
            """
                -- migrations level: homework
                create table if not exists checkers(
                    id serial primary key
                );
                
                create table if not exists homeworks(
                    id serial primary key,
                    name text not null,
                    start_date timestamp not null,
                    end_date timestamp not null,
                    statement text not null,
                    checker_id int not null references checkers.id 
                );
                
                create table if not exists submissions(
                    id serial primary key,
                    homework_id int not null references homeworks.id,
                    date timestamp not null,
                    link text not null
                );
                
                create table if not exists submission_results(
                    id serial primary key,
                    success boolean not null,
                    message text not null,
                    check_date timestamp not null,
                    submission_id int not null references submissions.id
                )
            """.trimIndent()
        )
    }
}

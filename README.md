# SqlQueryTranslator

Performs Query generation for Natural Language Inputs in Java.

## Conditionals.java - 
Class converts textual input into symbolic forms. To be used along with the 'WHERE' clause in an SQL Query. Checks for input tokens like 'Greater', 'Less', 'Above', 'Below', 'More', 'Less', 'Under', 'Over' to name a few.

## WhereClause.java -
Class generates the 'WHERE' clause of the query. Tests for the conditions using objects created by Conditionals class and appends them to the search query.

## QueryType.java-
Class generates the 'SELECT' clause which grabs all columns specified by the user while generating the SQL query.

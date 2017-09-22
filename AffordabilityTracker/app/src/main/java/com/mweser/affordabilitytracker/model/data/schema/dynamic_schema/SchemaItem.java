package com.mweser.affordabilitytracker.model.data.schema.dynamic_schema;

public class SchemaItem
{
    private String parentTable;
    private String sqlIdentifier;
    private String sqlTypeString;

    public SchemaItem(String parentTable, String sqlIdentifier, String sqlTypeString)
    {
        this.parentTable = parentTable;
        this.sqlIdentifier = sqlIdentifier;
        this.sqlTypeString = sqlTypeString;
    }

    public String getSqlTypeString()
    {
        return sqlTypeString;
    }

    public void setSqlTypeString(String sqlTypeString)
    {
        this.sqlTypeString = sqlTypeString;
    }

    public String getParentTable()
    {
        return parentTable;
    }

    public void setParentTable(String parentTable)
    {
        this.parentTable = parentTable;
    }

    public String getSqlIdentifier()
    {
        return sqlIdentifier;
    }

    public void setSqlIdentifier(String sqlIdentifier)
    {
        this.sqlIdentifier = sqlIdentifier;
    }
}

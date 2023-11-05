using Npgsql;

namespace Canal.Models;

public class PostgreSQL
{
    public static NpgsqlConnection? Connect()
    {
        string? conString = "Server=localhost;Port=5432;Username=postgres;Password=root;Database=sante";
        try
        {
            NpgsqlConnection con = new NpgsqlConnection(conString);
            return con;
        }
        catch (Exception)
        {
            Console.WriteLine("Erreur connection base postgres");
            throw;
        }
    }
}
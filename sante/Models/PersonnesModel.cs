using Canal.Models;
using Npgsql;

namespace sante.Models;

public class PersonnesModel
{
    public int Id_personne { get; set; }
    public string Nom_personne { get; set; }
    public DateOnly Dtn_personne { get; set; }
    public string Num_cin { get; set; }

    public PersonnesModel()
    {

    }

    public PersonnesModel(int Id_personne, string Nom_personne, DateOnly Dtn_personne, string Num_cin)
    {
        this.Id_personne = Id_personne;
        this.Nom_personne = Nom_personne;
        this.Dtn_personne = Dtn_personne;
        this.Num_cin = Num_cin;
    }

    public static List<PersonnesModel> GetAll()
    {
        List<PersonnesModel> res = new List<PersonnesModel>();
        NpgsqlConnection? con = PostgreSQL.Connect();
        con.Open();

        string req = "select * from personnes";
        NpgsqlCommand command = new NpgsqlCommand(req, con);
        NpgsqlDataReader reader = command.ExecuteReader();

        while (reader.Read())
        {
            int Id_personne = reader.GetInt32(reader.GetOrdinal("id_personne"));
            string Nom_personne = reader.GetString(reader.GetOrdinal("nom_personne"));
            DateOnly Dtn_personne = DateOnly.FromDateTime(reader.GetDateTime(reader.GetOrdinal("dtn_personne")));
            string Num_cin = reader.GetString(reader.GetOrdinal("num_cin"));
            res.Add(new PersonnesModel(Id_personne, Nom_personne, Dtn_personne, Num_cin));
        }

        reader.Close();
        con.Close();

        return res;
    }

    public static PersonnesModel GetOne(int Id_personne)
    {
        List<PersonnesModel> res = new List<PersonnesModel>();
        NpgsqlConnection? con = PostgreSQL.Connect();
        con.Open();

        string req = "select * from personnes where id_personne = " + Id_personne;
        NpgsqlCommand command = new NpgsqlCommand(req, con);
        NpgsqlDataReader reader = command.ExecuteReader();

        while (reader.Read())
        {
            string Nom_personne = reader.GetString(reader.GetOrdinal("nom_personne"));
            DateOnly Dtn_personne = DateOnly.FromDateTime(reader.GetDateTime(reader.GetOrdinal("dtn_personne")));
            string Num_cin = reader.GetString(reader.GetOrdinal("num_cin"));
            res.Add(new PersonnesModel(Id_personne, Nom_personne, Dtn_personne, Num_cin));
        }

        reader.Close();
        con.Close();

        if (res.Count == 0)
        {
            return null;
        }

        return res[0];
    }
}

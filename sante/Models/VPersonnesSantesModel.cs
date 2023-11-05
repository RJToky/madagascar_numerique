using Canal.Models;
using Npgsql;

namespace sante.Models;

public class VPersonnesSantesModel
{
    public PersonnesModel personne { get; set; }
    public List<AllergiesModel> allergies { get; set; }
    public List<VaccinsModel> vaccins { get; set; }
    public List<ChirurgiesModel> chirurgies { get; set; }
    public List<MaladiesModel> maladies { get; set; }

    public VPersonnesSantesModel()
    {

    }

    public VPersonnesSantesModel(PersonnesModel personne, List<AllergiesModel> allergies, List<VaccinsModel> vaccins, List<ChirurgiesModel> chirurgies, List<MaladiesModel> maladies)
    {
        this.personne = personne;
        this.allergies = allergies;
        this.vaccins = vaccins;
        this.chirurgies = chirurgies;
        this.maladies = maladies;
    }

    public static List<VPersonnesSantesModel> GetAll()
    {
        List<VPersonnesSantesModel> res = new List<VPersonnesSantesModel>();

        NpgsqlConnection? con = PostgreSQL.Connect();
        con.Open();

        string req = "select * from v_personnes_santes order by id_personne asc";
        NpgsqlCommand command = new NpgsqlCommand(req, con);
        NpgsqlDataReader reader = command.ExecuteReader();

        PersonnesModel personne = new PersonnesModel();
        List<AllergiesModel> allergies = new List<AllergiesModel>();
        List<VaccinsModel> vaccins = new List<VaccinsModel>();
        List<ChirurgiesModel> chirurgies = new List<ChirurgiesModel>();
        List<MaladiesModel> maladies = new List<MaladiesModel>();

        int Id_personne_prev = -1;
        while (reader.Read())
        {
            int Id_personne = reader.GetInt32(reader.GetOrdinal("id_personne"));
            string Nom_personne = reader.GetString(reader.GetOrdinal("nom_personne"));
            DateOnly Dtn_personne = DateOnly.FromDateTime(reader.GetDateTime(reader.GetOrdinal("dtn_personne")));
            string Num_cin = reader.GetString(reader.GetOrdinal("num_cin"));

            if (Id_personne_prev == -1)
            {
                Id_personne_prev = Id_personne;

                personne = new PersonnesModel(Id_personne, Nom_personne, Dtn_personne, Num_cin);

                if (!reader.IsDBNull(reader.GetOrdinal("id_allergie")))
                {
                    int? Id_allergie = reader.GetInt32(reader.GetOrdinal("id_allergie"));
                    string? Nom_allergie = reader.GetString(reader.GetOrdinal("nom_allergie"));

                    int temp = 0;
                    foreach (var item in allergies)
                    {
                        if (item.Id_allergie == Id_allergie)
                        {
                            temp++;
                            break;
                        }
                    }
                    if (temp == 0)
                    {
                        allergies.Add(new AllergiesModel(Id_allergie, Nom_allergie));
                    }
                }

                if (!reader.IsDBNull(reader.GetOrdinal("id_vaccin")))
                {
                    int? Id_vaccin = reader.GetInt32(reader.GetOrdinal("id_vaccin"));
                    string? Nom_vaccin = reader.GetString(reader.GetOrdinal("nom_vaccin"));

                    int temp = 0;
                    foreach (var item in vaccins)
                    {
                        if (item.Id_vaccin == Id_vaccin)
                        {
                            temp++;
                            break;
                        }
                    }
                    if (temp == 0)
                    {
                        vaccins.Add(new VaccinsModel(Id_vaccin, Nom_vaccin));
                    }
                }

                if (!reader.IsDBNull(reader.GetOrdinal("id_chirurgie")))
                {
                    int? Id_chirurgie = reader.GetInt32(reader.GetOrdinal("id_chirurgie"));
                    string? Nom_chirurgie = reader.GetString(reader.GetOrdinal("nom_chirurgie"));

                    int temp = 0;
                    foreach (var item in chirurgies)
                    {
                        if (item.Id_chirurgie == Id_chirurgie)
                        {
                            temp++;
                            break;
                        }
                    }
                    if (temp == 0)
                    {
                        chirurgies.Add(new ChirurgiesModel(Id_chirurgie, Nom_chirurgie));
                    }
                }

                if (!reader.IsDBNull(reader.GetOrdinal("id_maladie")))
                {
                    int? Id_maladie = reader.GetInt32(reader.GetOrdinal("id_maladie"));
                    string? Nom_maladie = reader.GetString(reader.GetOrdinal("nom_maladie"));

                    int temp = 0;
                    foreach (var item in maladies)
                    {
                        if (item.Id_maladie == Id_maladie)
                        {
                            temp++;
                            break;
                        }
                    }
                    if (temp == 0)
                    {
                        maladies.Add(new MaladiesModel(Id_maladie, Nom_maladie));
                    }
                }
            }
            else
            {
                if (Id_personne == Id_personne_prev)
                {
                    if (!reader.IsDBNull(reader.GetOrdinal("id_allergie")))
                    {
                        int? Id_allergie = reader.GetInt32(reader.GetOrdinal("id_allergie"));
                        string? Nom_allergie = reader.GetString(reader.GetOrdinal("nom_allergie"));

                        int temp = 0;
                        foreach (var item in allergies)
                        {
                            if (item.Id_allergie == Id_allergie)
                            {
                                temp++;
                                break;
                            }
                        }
                        if (temp == 0)
                        {
                            allergies.Add(new AllergiesModel(Id_allergie, Nom_allergie));
                        }
                    }

                    if (!reader.IsDBNull(reader.GetOrdinal("id_vaccin")))
                    {
                        int? Id_vaccin = reader.GetInt32(reader.GetOrdinal("id_vaccin"));
                        string? Nom_vaccin = reader.GetString(reader.GetOrdinal("nom_vaccin"));

                        int temp = 0;
                        foreach (var item in vaccins)
                        {
                            if (item.Id_vaccin == Id_vaccin)
                            {
                                temp++;
                                break;
                            }
                        }
                        if (temp == 0)
                        {
                            vaccins.Add(new VaccinsModel(Id_vaccin, Nom_vaccin));
                        }
                    }

                    if (!reader.IsDBNull(reader.GetOrdinal("id_chirurgie")))
                    {
                        int? Id_chirurgie = reader.GetInt32(reader.GetOrdinal("id_chirurgie"));
                        string? Nom_chirurgie = reader.GetString(reader.GetOrdinal("nom_chirurgie"));

                        int temp = 0;
                        foreach (var item in chirurgies)
                        {
                            if (item.Id_chirurgie == Id_chirurgie)
                            {
                                temp++;
                                break;
                            }
                        }
                        if (temp == 0)
                        {
                            chirurgies.Add(new ChirurgiesModel(Id_chirurgie, Nom_chirurgie));
                        }
                    }

                    if (!reader.IsDBNull(reader.GetOrdinal("id_maladie")))
                    {
                        int? Id_maladie = reader.GetInt32(reader.GetOrdinal("id_maladie"));
                        string? Nom_maladie = reader.GetString(reader.GetOrdinal("nom_maladie"));

                        int temp = 0;
                        foreach (var item in maladies)
                        {
                            if (item.Id_maladie == Id_maladie)
                            {
                                temp++;
                                break;
                            }
                        }
                        if (temp == 0)
                        {
                            maladies.Add(new MaladiesModel(Id_maladie, Nom_maladie));
                        }
                    }
                }
                else
                {
                    res.Add(new VPersonnesSantesModel(personne, allergies, vaccins, chirurgies, maladies));

                    Id_personne_prev = Id_personne;
                    personne = new PersonnesModel(Id_personne, Nom_personne, Dtn_personne, Num_cin);
                    allergies = new List<AllergiesModel>();
                    vaccins = new List<VaccinsModel>();
                    chirurgies = new List<ChirurgiesModel>();
                    maladies = new List<MaladiesModel>();

                    if (!reader.IsDBNull(reader.GetOrdinal("id_allergie")))
                    {
                        int? Id_allergie = reader.GetInt32(reader.GetOrdinal("id_allergie"));
                        string? Nom_allergie = reader.GetString(reader.GetOrdinal("nom_allergie"));

                        int temp = 0;
                        foreach (var item in allergies)
                        {
                            if (item.Id_allergie == Id_allergie)
                            {
                                temp++;
                                break;
                            }
                        }
                        if (temp == 0)
                        {
                            allergies.Add(new AllergiesModel(Id_allergie, Nom_allergie));
                        }
                    }

                    if (!reader.IsDBNull(reader.GetOrdinal("id_vaccin")))
                    {
                        int? Id_vaccin = reader.GetInt32(reader.GetOrdinal("id_vaccin"));
                        string? Nom_vaccin = reader.GetString(reader.GetOrdinal("nom_vaccin"));

                        int temp = 0;
                        foreach (var item in vaccins)
                        {
                            if (item.Id_vaccin == Id_vaccin)
                            {
                                temp++;
                                break;
                            }
                        }
                        if (temp == 0)
                        {
                            vaccins.Add(new VaccinsModel(Id_vaccin, Nom_vaccin));
                        }
                    }

                    if (!reader.IsDBNull(reader.GetOrdinal("id_chirurgie")))
                    {
                        int? Id_chirurgie = reader.GetInt32(reader.GetOrdinal("id_chirurgie"));
                        string? Nom_chirurgie = reader.GetString(reader.GetOrdinal("nom_chirurgie"));

                        int temp = 0;
                        foreach (var item in chirurgies)
                        {
                            if (item.Id_chirurgie == Id_chirurgie)
                            {
                                temp++;
                                break;
                            }
                        }
                        if (temp == 0)
                        {
                            chirurgies.Add(new ChirurgiesModel(Id_chirurgie, Nom_chirurgie));
                        }
                    }

                    if (!reader.IsDBNull(reader.GetOrdinal("id_maladie")))
                    {
                        int? Id_maladie = reader.GetInt32(reader.GetOrdinal("id_maladie"));
                        string? Nom_maladie = reader.GetString(reader.GetOrdinal("nom_maladie"));

                        int temp = 0;
                        foreach (var item in maladies)
                        {
                            if (item.Id_maladie == Id_maladie)
                            {
                                temp++;
                                break;
                            }
                        }
                        if (temp == 0)
                        {
                            maladies.Add(new MaladiesModel(Id_maladie, Nom_maladie));
                        }
                    }
                }
            }
        }
        res.Add(new VPersonnesSantesModel(personne, allergies, vaccins, chirurgies, maladies));

        reader.Close();
        con.Close();

        return res;
    }

    public static VPersonnesSantesModel GetByIdPersonne(int Id_personne)
    {
        List<VPersonnesSantesModel> res = VPersonnesSantesModel.GetAll();

        foreach (var item in res)
        {
            if (item.personne.Id_personne == Id_personne)
            {
                return item;
            }
        }

        return new VPersonnesSantesModel(null, new List<AllergiesModel>(), new List<VaccinsModel>(), new List<ChirurgiesModel>(), new List<MaladiesModel>());
    }

    public static VPersonnesSantesModel GetByNumCin(string Num_cin)
    {
        List<VPersonnesSantesModel> res = VPersonnesSantesModel.GetAll();

        foreach (var item in res)
        {
            if (item.personne.Num_cin == Num_cin)
            {
                return item;
            }
        }

        return new VPersonnesSantesModel(null, new List<AllergiesModel>(), new List<VaccinsModel>(), new List<ChirurgiesModel>(), new List<MaladiesModel>());
    }

}

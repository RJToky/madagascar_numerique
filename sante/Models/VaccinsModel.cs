using Canal.Models;
using Npgsql;

namespace sante.Models;

public class VaccinsModel
{
    public int? Id_vaccin { get; set; }
    public string? Nom_vaccin { get; set; }

    public VaccinsModel(int? Id_vaccin, string? Nom_vaccin)
    {
        this.Id_vaccin = Id_vaccin;
        this.Nom_vaccin = Nom_vaccin;
    }
}

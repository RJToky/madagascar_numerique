using Canal.Models;
using Npgsql;

namespace sante.Models;

public class MaladiesModel
{
    public int? Id_maladie { get; set; }
    public string? Nom_maladie { get; set; }

    public MaladiesModel(int? Id_maladie, string? Nom_maladie)
    {
        this.Id_maladie = Id_maladie;
        this.Nom_maladie = Nom_maladie;
    }
}

using Canal.Models;
using Npgsql;

namespace sante.Models;

public class AllergiesModel
{
    public int? Id_allergie { get; set; }
    public string? Nom_allergie { get; set; }

    public AllergiesModel(int? Id_allergie, string? Nom_allergie)
    {
        this.Id_allergie = Id_allergie;
        this.Nom_allergie = Nom_allergie;
    }
}

using Canal.Models;
using Npgsql;

namespace sante.Models;

public class ChirurgiesModel
{
    public int? Id_chirurgie { get; set; }
    public string? Nom_chirurgie { get; set; }

    public ChirurgiesModel(int? Id_chirurgie, string? Nom_chirurgie)
    {
        this.Id_chirurgie = Id_chirurgie;
        this.Nom_chirurgie = Nom_chirurgie;
    }
}

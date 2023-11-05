using sante.Models;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddControllersWithViews();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}"
);

app.MapGet("/personnes", () =>
{
    return PersonnesModel.GetAll();
});

app.MapGet("/personnes/{Id_personne}", (int Id_personne) =>
{
    return PersonnesModel.GetOne(Id_personne);
});

app.MapGet("/personnes_santes/{Id_personne}", (int Id_personne) =>
{
    return VPersonnesSantesModel.GetByIdPersonne(Id_personne);
});

app.MapGet("/personnes_santes", async (HttpContext context) =>
{
    string num_cin = context.Request.Query["num_cin"].ToString();
    if (!string.IsNullOrEmpty(num_cin))
    {
        var result = VPersonnesSantesModel.GetByNumCin(num_cin);
        if (result != null)
        {
            return Results.Ok(result);
        }
        else
        {
            return Results.NotFound();
        }
    }
    else
    {
        var result = VPersonnesSantesModel.GetAll();
        if (result != null)
        {
            return Results.Ok(result);
        }
        else
        {
            return Results.NotFound();
        }
    }
});

app.Run();

using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using sante.Models;

namespace sante.Controllers;

public class HomeController : Controller
{
    private readonly ILogger<HomeController> _logger;

    public HomeController(ILogger<HomeController> logger)
    {
        _logger = logger;
    }

    public IActionResult Index()
    {
        return View();
    }

    // public async void GetPerson()
    // {
    //     HttpClient httpClient = new HttpClient();
    //     string apiUrl = "http://localhost:8080/foncier/app/webservice/getPerson";

    //     HttpResponseMessage resp = await httpClient.GetAsync(apiUrl);

    //     if (resp.IsSuccessStatusCode)
    //     {
    //         string respBody = await resp.Content.ReadAsStringAsync();
    //         Console.WriteLine("Response : " + respBody);
    //     }
    //     else
    //     {
    //         Console.WriteLine("Error : " + resp.StatusCode);
    //     }
    // }

    [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
    public IActionResult Error()
    {
        return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
    }
}

var inputPoints = document.querySelector("#points");
var polygon = null;
var markers = [];
var points = [];

var map = L.map("map").setView([-18.897744026856127, 47.528639840003045], 13);

L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
  maxZoom: 19,
}).addTo(map);

map.addEventListener("click", (e) => {
  let lat = e.latlng.lat;
  let lng = e.latlng.lng;

  points.push([lat, lng]);
  placeMarker(lat, lng);
  placePolygon();

  let countPoint = inputPoints.childElementCount;
  let inputLat = document.querySelectorAll("input[name = 'latitude[]']")[
    countPoint - 1
  ];
  let inputLng = document.querySelectorAll("input[name = 'longitude[]']")[
    countPoint - 1
  ];

  if (!(inputLat.value === "" || inputLng.value === "")) {
    addInputFields("", "");
    updateFormFromMap(countPoint, lat, lng);
  } else {
    updateFormFromMap(countPoint - 1, lat, lng);
  }
});

document.querySelector("#ilatitude0").addEventListener("input", () => {
  updateMapFromForm(0);
});

document.querySelector("#ilongitude0").addEventListener("input", () => {
  updateMapFromForm(0);
});

document.querySelector("#nouveau_point").addEventListener("click", () => {
  let countPoint = inputPoints.childElementCount;
  let inputLat = document.querySelectorAll("input[name = 'latitude[]']")[
    countPoint - 1
  ];
  let inputLng = document.querySelectorAll("input[name = 'longitude[]']")[
    countPoint - 1
  ];
  if (!(inputLat.value === "" || inputLng.value === "")) {
    addInputFields("", "");
  }
});

document.querySelector("#supprimer_point").addEventListener("click", () => {
  let countPoint = inputPoints.childElementCount;

  rollBack();
  if (countPoint == 1) {
    updateFormFromMap(0, "", "");
  }

  if (countPoint > 1) {
    inputPoints.removeChild(inputPoints.children[countPoint - 1]);
  }

  handleBtnValider();
});

function handleBtnValider() {
  let countPoint = inputPoints.childElementCount;

  if (countPoint > 2) {
    document.querySelector("#valider_point").removeAttribute("disabled");
  } else {
    document.querySelector("#valider_point").setAttribute("disabled", "");
  }
}

function placeMarker(lat, lng) {
  markers.push(L.marker([lat, lng]));
  markers[markers.length - 1].addTo(map);
}

function placePolygon() {
  let polygonPoints = [];
  points.forEach((item) => {
    polygonPoints.push(item);
  });
  if (polygon != null) {
    map.removeLayer(polygon);
  }
  polygon = L.polygon(polygonPoints).addTo(map);
}

function drawTerrain(idPersonne) {
  var xhr = new XMLHttpRequest();

  xhr.open(
    "GET",
    `http://localhost:8080/foncier/app/proprietes?id_personne=${idPersonne}`,
    true
  );

  xhr.addEventListener("load", (e) => {
    let data = JSON.parse(e.target.responseText);

    data.forEach((item) => {
      let polygon = L.polygon(item["coordonnees"]).addTo(map);
      polygon.bindPopup(`
        <b style='font-size: 1.25rem;'>${item["adresse"]}</b>
        <p><b>Perimetre : </b>${item["perimetre"]} m</p>
        <p><b>Superficie </b>${item["superficie"]} ha</p>
      `);
    });
  });

  xhr.send(null);
}

function rollBack() {
  let countPoint = inputPoints.childElementCount;

  if (markers[countPoint - 1]) {
    map.removeLayer(markers[countPoint - 1]);
    points.pop();
    markers.pop();
    placePolygon();
  }
}

function addInputFields(lat, lng) {
  let countPoint = inputPoints.childElementCount;
  let inputPoint = document.createElement("div");
  inputPoint.classList.add("row");
  inputPoint.classList.add("mb-3");

  inputPoint.innerHTML = `
        <div class="col-6">
            <label for="ilatitude${countPoint}" class="form-label">Latitude</label>
            <input
                type="text" class="form-control" id="ilatitude${countPoint}" name="latitude[]" value="${lat}" required
            />
        </div>
        <div class="col-6">
            <label for="ilongitude${countPoint}" class="form-label">Longitude</label>
            <input
                type="text" class="form-control" id="ilongitude${countPoint}" name="longitude[]" value="${lng}" required
            />
        </div>
      `;
  inputPoints.appendChild(inputPoint.cloneNode(true));

  document
    .querySelector(`#ilatitude${countPoint}`)
    .addEventListener("input", () => {
      updateMapFromForm(countPoint);
    });

  document
    .querySelector(`#ilongitude${countPoint}`)
    .addEventListener("input", () => {
      updateMapFromForm(countPoint);
    });

  handleBtnValider();
}

function updateFormFromMap(index, lat, lng) {
  document.querySelector(`#ilatitude${index}`).value = lat;
  document.querySelector(`#ilongitude${index}`).value = lng;
}

function updateMapFromForm(index) {
  let lat = parseFloat(document.querySelector(`#ilatitude${index}`).value);
  let lng = parseFloat(document.querySelector(`#ilongitude${index}`).value);

  if (!isNaN(lat) && !isNaN(lng)) {
    rollBack();

    points.push([lat, lng]);
    placeMarker(lat, lng);
    placePolygon();
  }
}

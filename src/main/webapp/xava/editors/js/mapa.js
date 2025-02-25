openxava.addEditorInitFunction(function() {
  let mapDiv = document.getElementById("map");
  let coordenadasInput = document.getElementById("coordenadas");
  
  function initMap() {

    if (!mapDiv || !coordenadasInput) { return; }
    coordenadasInput.value = "40.566301, -1.314930";
    
    let latLng = { lat: 40.566301, lng: -1.314930 };

    let map = new google.maps.Map(mapDiv, {
        zoom: 16,
        center: latLng
    });

    let marker = new google.maps.Marker({
        position: latLng,
        map: map,
        draggable: true
    });

    google.maps.event.addListener(map, "click", function(event) {
        marker.setPosition(event.latLng);
        coordenadasInput.value = event.latLng.lat().toFixed(6) + "," + event.latLng.lng().toFixed(6);
    });
  }

    window.initMap = initMap;

    if (window.google && window.google.maps) {
        initMap();
        return;
    }

    let script = document.createElement("script");
    script.src = "https://maps.googleapis.com/maps/api/js?key=AIzaSyBsMpUxx6fPBo9rkGNuKFxNecoQXBPyjS0&callback=initMap";
    script.defer = true;
    script.async = true;
    document.body.appendChild(script);
    }
);
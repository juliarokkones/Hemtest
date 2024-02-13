// API Base URL - serveradressen
const API_BASE_URL = "http://localhost:3000";

// Fetch funktion som skickar en POST-begäran till servern
async function fetchWordFrequencies() {
        // Hämtar texten från input-fältet
        const textInput = document.getElementById("text-input").value;
        // Endpoint URL i servern
        const url = `${API_BASE_URL}/count`;

        // Om texten är tom, visa ett felmeddelande
        if (!textInput) {
            alert("Vänligen skriv in text");
            return;
        }
    // Skicka en POST-begäran till servern och sätter Content-Type till text/plain
    // Skickar texten från input-fältet som request body
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'text/plain'
            },
            body: textInput // Send the text input directly as the request body
        });

        // Om begäran lyckas, hämta data från response och skicka till renderWordFrequency-funktionen
        if (response.ok) {
            const data = await response.json();
            renderWordFrequency(data);

            // Om begäran misslyckas, visa ett felmeddelande
        } else {
            alert("Misslyckades att hämta data från servern");
            console.error(`Failed to fetch data. Status: ${response.status}`);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

// Funktion för att skriva data i en tabell
function renderWordFrequency(data) {
    try {
        // Hämtar tabell-container från HTML
        const tableContainer = document.getElementById("table-container");
        // Tömmer tabell-container
        tableContainer.innerHTML = "";

        // Skapar en tabell-header
        let table = "<table>";
        table += "<thead>";
        table += "<th>Word</th>";
        table += "<th>Frequency</th>";
        table += "</thead>";

        // Loopar genom response data och skapar tabellrader
        table += "<tbody>";
        for (var i = 0; i < data.length; i++) {
            table += "<tr>";
            table += "<td>" + data[i].word + "</td>";
            table += "<td>" + data[i].frequency + "</td>";
            table += "</tr>";
        }

        // Avslutar tabellen och skriver ut den i tabell-container
        table += "</tbody>";
        table += "</table>";
        tableContainer.innerHTML = table;
    } catch (error) {
        console.error('Error:', error);
    }
}

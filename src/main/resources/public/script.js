function getCompanyByNip(nip) {
    return fetch(`http://localhost:8080/subject/${nip}`)
        .then(res => {
            console.log(res);
            if(!res.ok) {
                handleError(res.status)
            }
            return res.json();
        });
}

function search() {
    $("#error-alert").hide();
    $("#company-data").hide();
    getCompanyByNip($("#nip").val())
        .then(res => fillCompanyData(res));
}

const handleError = (errCode) => {
    const errorAlertDiv = $("#error-alert");
    switch (errCode) {
        case 404:
            errorAlertDiv.html("Company with that NIP number not found");
            break;
        case 503:
            errorAlertDiv.html("GUR REGON service unavialable for now. Try again later.");
            break;
        default:
            errorAlertDiv.html("Unknown error happend. Try again later.");
    }
    errorAlertDiv.show();
};

function fillCompanyData(resJson) {
    const { regon, nip, name, address } = resJson;
    console.log(name);
    $("#company-name").html(name);
    $("#company-nip .content").html(nip);
    $("#company-regon .content").html(regon);
    fillCompanyDataAddress(address);
    $("#company-data").show();
}

function fillCompanyDataAddress(address) {
    const { region, place, postalCode, street, local } = address;
    $("#company-address-street-local").html(`${street} ${local}`);
    $("#company-address-city").html(`${postalCode}, ${place}`);
    $("#company-address-region").html(region);
}
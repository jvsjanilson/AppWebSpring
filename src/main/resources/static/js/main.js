function lookupMunicipiosByEstado(estado_id) {
        
    $.ajax({
        method: "get",
        url: `http://localhost:3030/api/municipios/${estado_id}`,
        success: (res) => {
            $("#municipio option").remove()
            if (res) {
                if (res.length > 0) {
                    res.forEach((i) => {
                        $("#municipio").append(`
                            <option value="${i.id}">${i.descricao}</option>
                        `);
                    });
                }
            }
        }
    })
}



$(".preco").maskMoney({prefix:'', allowNegative: false, thousands:'.', decimal:',', affixesStay: true, precision:2, selectAllOnFocus: true, allowZero: true});
$(".inteiro").maskMoney({prefix:'', allowNegative: false, thousands:'', decimal:'', affixesStay: false, precision:0, selectAllOnFocus: true, allowZero: true});
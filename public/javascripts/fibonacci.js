
/*
 * construct url based on values of index/size/fibtype fields
 */
function computeUrl() {
    // get index and size values
    index = $('#index').val();
    if (index == '') {
        index = 100;
    }
    size = $('#size').val();
    if (size == '') {
        size = 10;
    }

    // compute url based above input
    fibtype = $('input[name=fibtype]:checked').val();
    if (fibtype == 'range') {
        $('#size').prop('disabled', false);
        url = '/fibonacci/range?start=' + index + "&size=" + size;
    } else {
        $('#size').prop('disabled', true);
        url = '/fibonacci/' + fibtype + '/' + index;
    }
    $('#url').val(url);

}

/*
 * send ajax request to server get fibonacci number(s)
 */
function getFibNumbers() {
    urlvalue = $('#url').val();
    if (urlvalue == null || urlvalue == '') {
        urlvalue = '/fibonacci/list/100';
    }
    $.ajax({
        type : 'GET',
        url : urlvalue,
        success : function(data) {
            $("#result").val(JSON.stringify(data));
        },
        error : function (jqXHR, exception)  {
            alert('error: status=' + jqXHR.status + '\n' + jqXHR.responseText);

        }
    });
    return false;
}
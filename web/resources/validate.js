function validateForm() {
    var x = document.forms["transport"]["number"].value;
    var y = document.forms["transport"]["capacity"].value;
    var j = document.forms["transport"]["width"].value;
    var k = document.forms["transport"]["length"].value;
    if (x == "") {
        alert("Поле 'Номер состава' должно быть заполнено");
        return false;
    } else if (y == "") {
        alert("Поле 'Вместимость/Грузоподъмность' должно быть заполнено")
        return false;
    } else if (j == "") {
        alert("Поле 'Широта' должно быть заполнено")
        return false;
    } else if (k == "") {
        alert("Поле 'Долгота' должно быть заполнено")
        return false;
    }
}

function CreateFloatFilterMy(elementID, onChange) {
    try {
        inputKeyFilter.Create(elementID
            , onChange
            , function (elementInput, value) {//customFilter
                if (value.match(/^(-?\d{0,2})((\.(\d{0,6})?)?)$/i) == null) {
                    alert("Допустимый формат: введите координаты в пределах от 59.813756 до 60.081968 с.ш.(N) и от 30.208282 до 30.528259 в.д.(E)   p.s. правой кнопкой мыши можно отсюда скопировать :)")
                    inputKeyFilter.TextAdd("Допустимый формат: d{0,2}.d{0,6} Например: 59.939095 с.ш.(N), 30.315868 в.д.(E)"
                        , elementInput);
                    return false;
                }
                return true;
            }
        )
    } catch (e) {
        consoleError("Create float filter failed. " + e);
    }
}

function CreateDigitFilterMy(elementID, onChange) {
    try {
        inputKeyFilter.Create(elementID
            , onChange
            , function (elementInput, value) {//customFilter
                if (value.match(/^[0-9]{0,6}$/i) == null) {
                    alert("Допустимый формат: введите количество человеко-мест либо укажите грузоподъёмность в килограммах. Например: 30 человек или 30000 килограмм")
                    // if ( $.inArray( '06/04/2012', bank_holidays ) > -1 ) {
                    // if ( arr.indexOf( numberVal ) > -1 ) {
                    inputKeyFilter.TextAdd(""
                        , elementInput);
                    return false;
                }
                return true;
            }
        )
    } catch (e) {
        consoleError("Create float filter failed. " + e);
    }
}

function CreateNumberFilterMy(elementID, onChange) {

    // var numberVal = document.forms["transport"]["number"].value;
    // var arr = new Array();
    // arr = Packages.net.depo.dao.MainDAO.selectNumbers();
    // var fromJava = importPackage(net.depo.dao.MainDAO.selectNumbers());

    try {
        inputKeyFilter.Create(elementID
            , onChange
            , function (elementInput, value) {//customFilter
                if (value.match(/^[аАбБвВгГдДеЕёЁжЖзЗиИйЙкКлЛмМнНоОпПрРсСтТуУфФхХцЦчЧшШщЩъЪыЫьЬэЭюЮяЯ0-9]{0,5}$/i) == null) {
                    alert("Допустимый формат: буквенно-цифровые символы. Например: ТтАаКкИиЕе123456 ")
                    // if ( $.inArray( '06/04/2012', bank_holidays ) > -1 ) {
                    // if ( arr.indexOf( numberVal ) > -1 ) {
                    inputKeyFilter.TextAdd(""
                        , elementInput);
                    return false;
                }
                return true;
            }
        )
    } catch (e) {
        consoleError("Create float filter failed. " + e);
    }
}

function CreateParkNumberFilterMy(elementID, onChange) {
    try {
        inputKeyFilter.Create(elementID
            , onChange
            , function (elementInput, value) {//customFilter
                if (value.match(/^[0-9]{0,3}$/i) == null) {
                    alert("Допустимый формат: цифровые символы. Например: 1234567890 ")
                    // if ( $.inArray( '06/04/2012', bank_holidays ) > -1 ) {
                    // if ( arr.indexOf( numberVal ) > -1 ) {
                    inputKeyFilter.TextAdd(""
                        , elementInput);
                    return false;
                }
                return true;
            }
        )
    } catch (e) {
        consoleError("Create float filter failed. " + e);
    }
}
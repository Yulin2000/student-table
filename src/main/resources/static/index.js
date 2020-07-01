//pop up create form
function openCreate() {
    document.getElementById("createForm").style.display = "block";
}

//close create form
function closeCreate() {
    document.getElementById("createForm").style.display = "none";
}

//pop up modify form
function openModify() {
    if(HAVE_CHECKED === 0) {
        document.getElementById("modifyNotice").style.display = "block";
    }
    else {
        document.getElementById("modifyForm").style.display = "block";
    }
}

//pop up delete/modify notice page (no checked data)
function openNotice() {
    document.getElementById("modifyNotice").style.display = "block";
}

//close modify form
function closeModify() {
    document.getElementById("modifyForm").style.display = "none";
}

//------------------------------------checkbox and popup--------------------------------------

var HAVE_CHECKED = 0;

//add an id into array
function add(id) {
    document.getElementById(id).style.display = "";
    HAVE_CHECKED = HAVE_CHECKED + 1;
    document.getElementById("deletePosition").style.display = "";
    document.getElementById("deleteNoticePosition").style.display = "none";
}

//remove an id from array
function remove(id) {
    document.getElementById(id).style.display = "none";
    HAVE_CHECKED = HAVE_CHECKED - 1;
    if(HAVE_CHECKED === 0) {
        document.getElementById("deletePosition").style.display = "none";
        document.getElementById("deleteNoticePosition").style.display = "";
    }
}

//close no checked notice page
function closeModifyNotice() {
    document.getElementById("modifyNotice").style.display = "none";
}

//-------------------------------------Display by page-----------------------------------------

var NUMBER_OF_DATA = 0;
var CURR = 0;

//accumulate the number of date
function accumulate() {
    NUMBER_OF_DATA = NUMBER_OF_DATA + 1;
}

//display page divider
function dividePage() {
    //helper function: jump to a certain page
    skipPage = function() {
        var pageNumber = this.innerHTML;

        for(var j = 0; j < NUMBER_OF_DATA; ++j) {
            document.getElementById(getId(j)).style.display = "none";
        }
        CURR = pageNumber * 10 - 20;
        nextPage();
    };

    var numberOfPage = Math.ceil(NUMBER_OF_DATA / 10);
    var parentElement = document.getElementById("page-divider");
    for(var i = 1; i <= numberOfPage; ++i) {
        var childElement = document.createElement("button");
        childElement.type = "button";
        childElement.innerHTML = i;
        childElement.id = getPageId(i);
        childElement.className = "pageButton";
        childElement.onclick = skipPage;
        parentElement.appendChild(childElement);
    }
}

//indicate current page
function colorPage() {
    var numberOfPage = Math.ceil(NUMBER_OF_DATA / 10);
    for(var i = 1; i <= numberOfPage; ++i) {
        document.getElementById(getPageId(i)).style = "color = black";
    }
    var currPage = CURR / 10 + 1;
    document.getElementById(getPageId(currPage)).style = "color: red";
}

//get id
function getId(id) {
    return "No." + id.toString();
}

//get page id
function getPageId(id) {
    return "Page" + id.toString();
}

//button display
function displayButton() {
    document.getElementById("prev").style.display = "";
    document.getElementById("next").style.display = "";
    if(CURR - 10 < 0) {
        document.getElementById("prev").style.display = "none";
    }
    if(CURR + 10 >= NUMBER_OF_DATA) {
        document.getElementById("next").style.display = "none";
    }
}

//first page and call default functions
function firstPage() {
    //show the bottons of the first page
    displayButton();
    document.getElementById(getId(CURR)).style.display = "";
    //show itself and next 9 elements
    for(var i = 1; i <= 9; ++i) {
        if(CURR + i < NUMBER_OF_DATA) {
            document.getElementById(getId(CURR + i)).style.display = "";
        }
    }

    //first default function: display NUMBER_OF_DATA
    document.getElementById("data-number").innerHTML = NUMBER_OF_DATA;
    //second default function: display divided pages
    dividePage();
    colorPage();
}

//next page
function nextPage() {
    //move to the next page and show the buttons of that page
    CURR = CURR + 10;
    displayButton();
    document.getElementById(getId(CURR)).style.display = "";
    //show itself and next 9 elements, then clean the previous 10 elements
    for(var i = 1; i <= 10; ++i) {
        if(CURR - i >= 0) {
            document.getElementById(getId(CURR - i)).style.display = "none";
        }
        if(i < 10 && CURR + i < NUMBER_OF_DATA) {
            document.getElementById(getId(CURR + i)).style.display = "";
        }
    }
    colorPage();
}

//previous page
function prevPage() {
    document.getElementById(getId(CURR)).style.display = "none";
    //clean itself and next 9 elements, then show the previous 10 elements
    for(var i = 1; i <= 10; ++i) {
        if(i < 10 && CURR + i < NUMBER_OF_DATA) {
            document.getElementById(getId(CURR + i)).style.display = "none";
        }
        if(CURR - i >= 0) {
            document.getElementById(getId(CURR - i)).style.display = "";
        }
    }
    //move to the previous page and display the buttons of that page
    CURR = CURR - 10;
    displayButton();
    colorPage();
}


//---------------------------------------alternatecolor--------------------------------
function altRows(id){
    if(document.getElementsByTagName){ 
         
        var table = document.getElementById(id); 
        var rows = table.getElementsByTagName("tr");
          
        for(i = 0; i < rows.length; i++){         
            if(i % 2 == 0){
                rows[i].className = "evenrowcolor";
            }else{
                rows[i].className = "oddrowcolor";
            }     
        }
    }
}

window.onload = function() {
    altRows('alternatecolor');
}



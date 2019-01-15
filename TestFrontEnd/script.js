var http = new XMLHttpRequest();
var URL = 'http://localhost:7070/';
var accounts = [];
var currentAccountIndex = 0;
getAccounts();

/*General functions*/

//Create list of accounts
function getAccountsList(array) {
    var accountsList = document.getElementById("accountsList");
    accountsList.innerHTML = "";
    console.log(array);
    for (var i = 0; i < array.length; i++) {
        var newLi = document.createElement('li');
        newLi.value = i;
        liEvents(newLi);
        var newAccount = accountsList.appendChild(newLi);
        newAccount.innerHTML = array[i].number + " | " + array[i].owner + " | " + array[i].sum;
        if(i == currentAccountIndex) {
            newAccount.style.backgroundColor = "lightgrey";
        }
    }

}


/*Account list*/

//HTTP - Get list of accounts
function getAccounts() {
    var method = 'GET';
    var name = 'accounts';
    http.open(method, URL + name);
    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE && http.status == 200) {
            accounts = JSON.parse(http.responseText);
            console.log("HTTP");
            console.log(http);
            getAccountsList(accounts);
        } else if (http.readyState === XMLHttpRequest.DONE && http.status != 200) {
            console.log("error");
        }
    }
    http.send();
    console.log("HTTP SENT");
}


/*select account from list of accounts and define indexes of recipient and donor accounts*/
function liEvents(item) {
    item.addEventListener('click', itemClickListener);
}

//styling accounts List items on click and define recipient and donor undexes
function itemClickListener(event) {
    var item = document.querySelectorAll("li");
    item.forEach(function (value, i, arr) {
        value.style.backgroundColor = "white";
    });
    event.target.style.backgroundColor = "lightgrey";
    currentAccountIndex = event.target.getAttribute('value');
}




/*------------------Section Transfer Money--------------------------*/

var btnFrom = document.getElementById("btnFromAccount");
var btnTo = document.getElementById("btnToAccount");
var btnSubmitTransfer = document.getElementById("onSubmitTransfer");
var inputFrom = document.getElementById('donorAccount');
var inputTo = document.getElementById('recipientAccount');
var moneyToTransfer = document.getElementById("sumToTransfer");
var transferMessage= document.getElementById("transferMessage");

// get source Account Number
btnFrom.addEventListener('click', fromAccount);

function fromAccount() {
    inputFrom.value = accounts[currentAccountIndex].number;
}

// get recipient Account Number
btnTo.addEventListener('click', toAccount);

function toAccount() {
    inputTo.value = accounts[currentAccountIndex].number;
}

// submit
btnSubmitTransfer.onclick = function () {
    var method = 'PUT';
    var name = 'transfer';
    var body = {
        accountFrom: inputFrom.value,
        accountTo: inputTo.value,
        sum: +moneyToTransfer.value
    };
    http.open(method, URL + name);
    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE && http.status == 200) {
            console.log("money are transfered");
            console.log(http.responseText);
            transferMessage.innerText = http.responseText;
            getAccounts();
        } else if (http.readyState === XMLHttpRequest.DONE && http.status != 200) {
            console.log("error");
            console.log(http.responseText);
            transferMessage.innerText = http.responseText;
        }

    };
    http.send(JSON.stringify(body));
    inputFrom.value = null;
    inputTo.value = null;
    moneyToTransfer.value = null;
};

/*---------------End section Transfer Money*------------------------/

/*----------------------Section Deposit Money ------------------------*/
var btnDeposit = document.getElementById("btnDepositAccount");
var inputDepositAccount = document.getElementById("depositAccount");
var inputDepositSum = document.getElementById("depositSum");
var btnOnDeposit = document.getElementById("onPutMoney");
var depositMessage= document.getElementById("depositMessage");

btnDeposit.onclick = function () {
    inputDepositAccount.value = accounts[currentAccountIndex].number;
};

btnOnDeposit.onclick = function (ev) {
    var name = 'deposit';
    var depositBody = {
        accountFrom: "",
        accountTo: inputDepositAccount.value,
        sum: +inputDepositSum.value
    };
    http.open('PUT', URL + name);
    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE && http.status == 200) {
            console.log("money are transfered");
            console.log(http.responseText);
            depositMessage.innerText = http.responseText;
            getAccounts();
        } else if (http.readyState === XMLHttpRequest.DONE && http.status != 200) {
            console.log("error");
            console.log(http.responseText);
            depositMessage.innerText = http.responseText;
        }
    };
    http.send(JSON.stringify(depositBody));
    inputDepositAccount.value = null;
    inputDepositSum.value = null;
};

/*---------------------End-Section Deposit Money ------------------------*/


/*---------------------Section Withdraw Money ------------------------*/

var btnWithdraw = document.getElementById("btnWithdrawAccount");
var inputWithdrawAccount = document.getElementById("withdrawAccount");
var inputWithdrawSum = document.getElementById("withdrawSum");
var btnOnWithdraw = document.getElementById("onGetMoney");
var withdrawMessage= document.getElementById("withdrawMessage");

btnWithdraw.onclick = function () {
    inputWithdrawAccount.value = accounts[currentAccountIndex].number;
};

btnOnWithdraw.onclick = function (ev) {
    var name = 'withdraw';
    var withdrawBody = {
        accountFrom: "",
        accountTo: inputWithdrawAccount.value,
        sum: +inputWithdrawSum.value
    };
    http.open('PUT', URL + name);
    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE && http.status == 200) {
            console.log("money are transfered");
            console.log(http.responseText);
            withdrawMessage.innerText = http.responseText;
            getAccounts();
        } else if (http.readyState === XMLHttpRequest.DONE && http.status != 200) {
            console.log("error");
            console.log(http.responseText);
            withdrawMessage.innerText = http.responseText;
        }
    };
    http.send(JSON.stringify(withdrawBody));
    inputWithdrawAccount.value = null;
    inputWithdrawSum.value = null;
};


/*---------------------End-Section Withdraw Money ------------------------*/


/*---------------------Section-New Account ------------------------*/


var inputNewAccount = document.getElementById("newAccountOwner");

var btnOnNewAccount = document.getElementById("onNewAccount");
var newAccountMessage = document.getElementById("newAccountMessage");


btnOnNewAccount.onclick = function (ev) {
    var name = 'account';
    var accountBody = {
        name: inputNewAccount.value,

    }
    http.open('POST', URL + name);
    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE && http.status == 201) {
            console.log("account is created");
            newAccountMessage.innerText = http.responseText;
            getAccounts();
        } else if (http.readyState === XMLHttpRequest.DONE && http.status >= 300) {
            console.log("error");
            newAccountMessage.innerText = http.responseText;
        }
    };
    http.send(JSON.stringify(accountBody));
    inputNewAccount.value = null;

}

/*---------------------End-Section New Account ------------------------*/
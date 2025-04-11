const insideCard = document.getElementById('insideCard')

window.onload = () => {
    try {
        fetch(`https://wizzie.online/BusTracking/functions.php?condition=getStudentsOnlysdhjdfdjkfdjkfd`).then(data => {
            if (!data.ok) {
                alert("Eroor")
            }
            return data.json()
        }).then(json => {
            var text = ""
            json.data.forEach(element => {
                if (element.name.length != 0) {
                    text += `<div class='helloIam' onclick='opening("${element.id}","${element.name}","${element.mail}")'><big>Roll Number : ${element.rollNumber}</big><br>${element.busId} : Requests</br><br>${element.name} 
                        <br> ${element.mail} </div>`


                }
            });
            insideCard.innerHTML = text
        })
    } catch (error) {
        alert(error)
    }

    loadlocationOfBuses()
}

const tagPoint = document.getElementById('busesList')
function loadlocationOfBuses() {

    fetch('https://wizzie.online/BusTracking/functions.php?condition=getBusDetailsByAdmin').then(text => text.json()).then(
        data => {
            var num = 0
            var text = ""
            data.data.forEach(view => {
                text += `
                        <div class="viewOppsitre">
        <h2>Bus Details</h2>
        // <p><span>ID: </span> ${view.id}</p>
        <p><span>Name:</span> ${view.name}</p>
        <p><span>Bus Number:</span> ${view.busNumber}</p>
        <p><span>Seats Available:</span> ${view.seats}</p>
        <p><span>Stops:</span> ${view.stops}</p>
        <p><span>From:</span>${view.fromPlace}</p>
        <p><span>To:</span> ${view.toPlace}</p>

        <div style='width:100%;align-items:center;display:flex;'>
        <div onclick='Targetted(${num})'style='box-shadow:2px 2px 2px 2px #000;border-radius:20px;background:#000;width:fit-content;align-items:center;display:flex;' >

           <svg xmlns="http://www.w3.org/2000/svg" width="24px" height="24px" viewBox="0 0 24 24" fill="none">
<path d="M12 21C15.5 17.4 19 14.1764 19 10.2C19 6.22355 15.866 3 12 3C8.13401 3 5 6.22355 5 10.2C5 14.1764 8.5 17.4 12 21Z" stroke="#FFF" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
</svg>
        <p style='color:white;padding:10px'>Location</p>
        
        </div>
             <div onclick='updateBusDetails("${view.name}", "${view.busNumber}","${view.seats}","${view.stops}", "${view.fromPlace}", "${view.toPlace}","${view.id}")'
             style='box-shadow:2px 2px 2px 2px #000;border-radius:20px;background:#000;width:fit-content;align-items:center;display:flex;margin-left:10px;padding:10px;' >

           <img src='https://cdn-icons-png.flaticon.com/512/1159/1159633.png' style='width:24px;height:24px;filter:invert(100%);'>

        <p style='color:white;margin-left:10px;'>Edit</p>
        
        </div>
        </div>
     
       
        
    </div>
                    `
                num++
            })
            tagPoint.innerHTML = text
        }
    )

}
function Targetted(params) {
    if (params == 0) {
        fetch('https://api.thingspeak.com/channels/2820190/feeds.json?api_key=XH63OCLCVUJH1T2B&results=1').then(view => view.json()).then(data => {
            var lat = data.feeds[0].field1
            var lon = data.feeds[0].field2
            if (lat == null || lat == "0" || lon == null || lon == "0") {
                toast(`Coordinates are not Good ,${lat},${lon}`)
            } else {
                window.location.href = `maps.html?lat=${lat}&lon=${lon}`

            }
        })

    } else {
        toast('Sorry To Find The Location')
    }
}


function updateBusDetails(busName, busNumber, seatsCapacity, stops, fromPoint, toPoint, id) {
    document.getElementById("busName").value = busName
    document.getElementById("busNumber").value = busNumber
    document.getElementById("seatsCapacity").value = seatsCapacity
    document.getElementById("stops").value = stops
    document.getElementById("fromPoint").value = fromPoint
    document.getElementById("toPoint").value = toPoint
    document.getElementById('myInputId').value=id
    addBusDetails("sjfkdsf")


}



const helloIam = document.querySelectorAll('helloIam')

const cardPointer = document.getElementById('cardPointer')

function opening(params, name, mail) {
    viewVision.classList.replace('dialognone', 'dialog')
    cardPointer.innerHTML = `<div class="slightGood" id="loaderPoint" style="margin-top: 5%;">

            <div style="background-color: white;height: fit-content;border-radius: 20px;margin-top: 0px;">

                <div class="loader"></div>
            </div>
        </div>`
    loadMyDetails(params, name, mail)
}






function loadMyDetails(params, name, mail) {

    const viewVision = document.getElementById('viewVision');

    var url = `https://wizzie.online/BusTracking/functions.php?condition=getRequestDetailsslfjsldkfjsdklfjsdklfjsdklfjdsklfjsdfkljsdf&id=${params}`;
    fetch(url).then(data => data.json()).then(view => {
        cardPointer.innerHTML = ``
        if ("Failed" == view.message) {
            closeVoid()
            return
        }


        var text = ""

        view.data.forEach(element => {
            const date = parseInt(element.uploadDate)
            const dateView = new Date(date);
            const options = {
                weekday: 'short',
                day: '2-digit',
                month: 'short',
                year: 'numeric',
                hour: '2-digit',
                minute: '2-digit',
                second: '2-digit',
                timeZoneName: 'short'
            }
            const formated = new Intl.DateTimeFormat('en-US', options).format(dateView)

            text += `
               <div class="card">
        <img src="${element.image}" alt="Applied Image">
        <div class="card-content">
            <h3>Applied on </h3>
            <p class="date">Applied on: <span id="appliedDate">${formated}</span></p>
        </div>
    </div>
            `
        })

        viewVision.style = ``
        if (view.data.length == 0) {
            cardPointer.innerHTML = `<img src="https://cdn-icons-png.flaticon.com/128/5058/5058446.png" alt="" srcset="">`
            return
        }
        cardPointer.innerHTML = `
         <div class='viewPoi' onclick='closeVoid()'><img src='http://cdn-icons-png.flaticon.com/512/2734/2734822.png'></div>
        <p style='color:white;'>Requests :</p><br>
               
        <div  class='worldPoint'> 


        ${text}
        </div> <br>
        <div  style='display:flex;justify-content:end;margin-left:20px;'>
        <button onclick='assignViewPointer("${params}","${name}","${mail}")' style='margin-bottom:50px'>Assign a Bus</button>
        </div>
    
        `
    })


}
function assignViewPointer(id, name, mail) {
    const url = `assign.html?id=${encodeURIComponent(id)}&name=${encodeURIComponent(name)}&mail=${encodeURIComponent(mail)}`
    window.location.href = url
}



const viewVision = document.getElementById('viewVision')

function closeVoid() {
    viewVision.classList.replace('dialog', 'dialognone')
}


const innerCard = document.getElementById("addBusDialog")




function addBusDetails(para) {
    innerCard.classList.replace('dialognone', 'addBusDialog')
    if (para == 'sjfkdsf') {
        document.getElementById('viewVisionPart').innerText = 'Update Values'


        document.getElementById('deleteView').style='visibility:visible;display:block;'
        document.getElementById('updateClick').style='visibility:visible;display:block;margin-left:10px;'
        document.getElementById('clickView').style='visibility:hidden;display:none;'
   
    } else {
        document.getElementById('updateClick').style='visibility:hidden;display:none;'
        document.getElementById('clickView').style='visibility:visible;display:block;'
        document.getElementById('deleteView').style='visibility:hidden;display:none;'
        
        document.getElementById('busName').value = ''
        document.getElementById('busNumber').value = ''
        document.getElementById('seatsCapacity').value = ''
        document.getElementById('stops').value = ''
        document.getElementById('fromPoint').value = ''
        document.getElementById('toPoint').value = ''
        document.getElementById('viewVisionPart').innerText = 'Add Values'
    }
    closeProgress()
}

function closDialog() {
    innerCard.classList.replace('addBusDialog', 'dialognone')
}


document.getElementById('clickView').addEventListener('click', () => {
    const busName = document.getElementById('busName').value
    const busNumber = document.getElementById('busNumber').value
    const seatsCapacity = document.getElementById('seatsCapacity').value
    const stops = document.getElementById('stops').value
    const fromPoint = document.getElementById('fromPoint').value
    const toPoint = document.getElementById('toPoint').value

    if (busName.length == 0) {
        toast("Please enter  bus name")
    } else if (busNumber.length == 0) {
        toast("Please enter  bus number")

    } else if (seatsCapacity.length == 0) {
        toast("Please enter  bus Seats")

    } else if (stops.length == 0) {
        toast("Please enter  bus Stop Details")

    } else if (fromPoint.length == 0) {
        toast("Please enter From Details")

    } else if (toPoint.length == 0) {
        toast("Please enter to (Destination Details)")

    } else {
        closeVoid()
        closDialog()
        loadProgress()
        const url = `https://wizzie.online/BusTracking/addBus.php?name=${busName}&busNumber=${busNumber}&seats=${seatsCapacity}&stops=${stops}&fromPlace=${fromPoint}&toPlace=${toPoint}`
        fetch(url).then(data => data.json()).then(view => {
            toast(view.message)
            if (view.message == "Sucesss") {
                document.getElementById('busName').value = ''
                document.getElementById('busNumber').value = ''
                document.getElementById('seatsCapacity').value = ''
                document.getElementById('stops').value = ''
                document.getElementById('fromPoint').value = ''
                document.getElementById('toPoint').value = ''
                closeVoid()
                closDialog()
                closeProgress()
                return
            }

            addBusDetails()
        })

    }


})



const loaderPoint = document.getElementById('loaderPoint')

function loadProgress() {
    loaderPoint.classList.replace('dialognone', 'slightGood')
}
function closeProgress() {
    loaderPoint.classList.replace('slightGood', 'dialognone')
}


document.addEventListener('keydown', function (event) {
    if (event.key === "Escape") {
        closDialog()
        closeVoid()
        closeProgress()

    }
})

const item = document.getElementById('item')

function toast(message) {
    item.innerText = message
    item.className = "item"
    setTimeout(() => {
        item.className = item.className.replace('item', 'item2')
    }, 1500)
}


function deletFunctions(){
    const id=document.getElementById('myInputId').value
    fetch(`https://wizzie.online/BusTracking/delete.php?id=${id}`).then(view=>view.json()).then(
        pot=>{
            if(pot.message=="Success"){
                alert('Updated')
                window.location.reload('')
            }
        })
}
function updateFunctons(){
    const busName=document.getElementById("busName").value
    const busNumber=document.getElementById("busNumber").value
    const seatsCapacity=document.getElementById("seatsCapacity").value
    const stops=document.getElementById("stops").value
    const fromPoint=document.getElementById("fromPoint").value
    const toPoint=document.getElementById("toPoint").value
    const id=document.getElementById('myInputId').value
    fetch(`https://wizzie.online/BusTracking/updateFun.php?name=${busName}&busNumber=${busNumber}&seats=${seatsCapacity}&stops=${stops}&fromPlace=${fromPoint}&toPlace=${toPoint}&id=${id}`).then(view=>view.json()).then(
    pot=>{
        if(pot.message=="Success"){
            alert('Updated')
            window.location.reload('')
        }
    })
}

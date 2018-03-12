(function(){

    const config={
        apiKey: "AIzaSyCPUTWuSu4URIAsdLh2edLavzwwD5wN2AQ",
        authDomain: "geolocation-e1e59.firebaseapp.com",
        databaseURL: "https://geolocation-e1e59.firebaseio.com/",
        storageBucket: "gs://geolocation-e1e59.appspot.com"
    };
    firebase.initializeApp(config);

    const preObject= document.getElementById("object");

    const dbRefObject= firebase.database().ref().child('heading');

    const RefObject= firebase.database().ref().child('object');
    RefObject.on('value', snap=>{
        preObject.innerText= JSON.stringify(snap.val(), null, 3);
    })
    dbRefObject.on('value', snap => console.log(snap.val()));

    const divlocationJson= document.getElementById("locationJson");
    const ReflocationJson= firebase.database().ref().child('GPS');

    ReflocationJson.on('value', snap=>{
        divlocationJson.innerText= JSON.stringify(snap.val(), null, 3);
        console.log(snap.val());
    })

    const list= document.getElementById('listLocation');
    const RefListLocation= firebase.database().ref().child('GPS');
    for(i=1;i<50;i++)
    {
        const RefList= RefListLocation.child('location'+i);
        RefList.on('child_added', snap => {
            const li= document.createElement('li');
            li.innerText= snap.val();
            list.appendChild(li);
        })
    }
}());
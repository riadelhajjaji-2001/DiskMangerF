const url="http://localhost:8081/manager/v1/users_home_directories"
const myFolders=[
    {
        "id": 1,
        "name": "root",
        "owner": "all",
        "parentFolder": null,
        "taille": 0
    },
    {
        "id": 2,
        "name": "riadHome",
        "owner": "riad",
        "parentFolder": {
            "id": 1,
            "name": "root",
            "owner": "all",
            "parentFolder": null,
            "taille": 0
        },
        "taille": 0
    },
    {
        "id": 3,
        "name": "medHome",
        "owner": "med",
        "parentFolder": {
            "id": 1,
            "name": "root",
            "owner": "all",
            "parentFolder": null,
            "taille": 0
        },
        "taille": 0
    },
    {
        "id": 4,
        "name": "riadSubfolder1",
        "owner": "riad",
        "parentFolder": {
            "id": 2,
            "name": "riadHome",
            "owner": "riad",
            "parentFolder": {
                "id": 1,
                "name": "root",
                "owner": "all",
                "parentFolder": null,
                "taille": 0
            },
            "taille": 0
        },
        "taille": 0
    },
    {
        "id": 5,
        "name": "medSubfolder1",
        "owner": "med",
        "parentFolder": {
            "id": 3,
            "name": "medHome",
            "owner": "med",
            "parentFolder": {
                "id": 1,
                "name": "root",
                "owner": "all",
                "parentFolder": null,
                "taille": 0
            },
            "taille": 0
        },
        "taille": 0
    },
    {
        "id": 6,
        "name": "riadSubfolder2",
        "owner": "riad",
        "parentFolder": {
            "id": 2,
            "name": "riadHome",
            "owner": "riad",
            "parentFolder": {
                "id": 1,
                "name": "root",
                "owner": "all",
                "parentFolder": null,
                "taille": 0
            },
            "taille": 0
        },
        "taille": 0
    },
    {
        "id": 7,
        "name": "medSubfolder2",
        "owner": "med",
        "parentFolder": {
            "id": 3,
            "name": "medHome",
            "owner": "med",
            "parentFolder": {
                "id": 1,
                "name": "root",
                "owner": "all",
                "parentFolder": null,
                "taille": 0
            },
            "taille": 0
        },
        "taille": 0
    },
    {
        "id": 8,
        "name": "riadSubfolder3",
        "owner": "riad",
        "parentFolder": {
            "id": 2,
            "name": "riadHome",
            "owner": "riad",
            "parentFolder": {
                "id": 1,
                "name": "root",
                "owner": "all",
                "parentFolder": null,
                "taille": 0
            },
            "taille": 0
        },
        "taille": 0
    }
]
const getRootDirectory=async()=>{
        let rootFolders=[];
        let fetchData=await fetch(url,
                    {
                        mode:'no-cors'
                    })
         rootFolders= fetchData.response();
         rootFolders.map(folderO=>{
                let folder=document.createElement('div');
                folder.setAttribute('class','folder');
                let folderIm=document.createElement('img');
                folderIm.setAttribute("src","folder.jpg");
                folderIm.setAttribute("class","folderIcon");
                folderIm.setAttribute("width","30px");
                let fodlerName=document.createElement("small");
                fodlerName.setAttribute("class","folderName");
                let Name=document.createTextNode(folderO.Name);
                fodlerName.appendChild(Name);
                folder.appendChild(folderIm);
                folder.appendChild(fodlerName);
                document.getElementById("navigator").appendChild(folder);
        })
    };
   

 



document.addEventListener("click",async()=>await getRootDirectory())
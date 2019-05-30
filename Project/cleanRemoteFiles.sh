
bold=$(tput bold)
normal=$(tput sgr0)
echo "${bold}*** Script de Limpeza das Máquinas ***${normal}"

export SSHPASS='enterro2019'


###

echo -e "\n${bold}->${normal} A eliminar Registry máquina ${bold}1${normal}"
sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws01.ua.pt << EOF
	rm -rf Registry
	cd Public
	rm -rf dir_registry
	exit
EOF

echo -e "\n${bold}->${normal} A eliminar Repair Area da máquina ${bold}2${normal}"
sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws02.ua.pt << EOF
	rm -rf RepairArea
	cd Public
	rm -rf classes
	exit
EOF

echo -e "\n${bold}->${normal} A eliminar Park da máquina ${bold}3${normal}"
sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws03.ua.pt << EOF
	rm -rf Park
	cd Public
	rm -rf classes
	exit
EOF

echo -e "\n${bold}->${normal} A eliminar Supplier Site da máquina ${bold}4${normal}"
sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws04.ua.pt << EOF
	rm -rf SupplierSite
	cd Public
	rm -rf classes
	exit
EOF

echo -e "\n${bold}->${normal} A eliminar Outside World da máquina ${bold}5${normal}"
sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws05.ua.pt << EOF
	rm -rf OutsideWorld
	cd Public
	rm -rf classes
	exit
EOF

echo -e "\n${bold}->${normal} A eliminar Lounge da máquina ${bold}6${normal}"
sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws06.ua.pt << EOF
	rm -rf Lounge
	cd Public
	rm -rf classes
	exit
EOF

echo -e "\n${bold}->${normal} A eliminar Customer da máquina ${bold}7${normal}"
sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws07.ua.pt << EOF
	rm -rf Customer
	cd Public
	rm -rf classes
	exit
EOF

echo -e "\n${bold}->${normal} A eliminar Logger da máquina ${bold}8${normal}"
sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws08.ua.pt << EOF
	rm -rf GeneralInformationRepo
	cd Public
	rm -rf dir_logger
	exit
EOF

echo -e "\n${bold}->${normal} A eliminar Mechanic da máquina ${bold}10${normal}"
sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws10.ua.pt << EOF
	rm -rf Mechanic
	cd Public
	rm -rf classes
	exit
EOF

echo -e "\n${bold}->${normal} A eliminar Manager da máquina ${bold}9${normal}"
sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws09.ua.pt << EOF
	rm -rf Manager
	cd Public
	rm -rf classes
	exit
EOF

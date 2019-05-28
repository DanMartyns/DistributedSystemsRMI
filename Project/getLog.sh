bold=$(tput bold)
normal=$(tput sgr0)
echo "\n${bold}*** Script de Recolha do Log ***${normal}"

export SSHPASS='enterro2019'

###

echo -e "\n${bold}* Recolha do log no nó *${normal}"

echo -e "\n${bold}->${normal} A recolher o ficheiro de log da máquina ${bold}1${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws01.ua.pt << EOF
	cd Public/dir_registry/classes
	get -r nohup.out Results/nohupREG.out
	cd ../..
	cd dir_logger/classes/
	get -r nohup.out Results/nohupLOG.out
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printRepairArea para a máquina ${bold}2${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws02.ua.pt << EOF
	cd Public/classes/
	get -r nohup.out Results/nohupREPA.out
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printPark para a máquina ${bold}3${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws03.ua.pt << EOF
	cd Public/classes/
	get -r nohup.out Results/nohupPark.out
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printSupplierSite para a máquina ${bold}4${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws04.ua.pt << EOF
	cd Public/classes/
	get -r nohup.out Results/nohupSup.out
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printOutsideWorld para a máquina ${bold}5${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws05.ua.pt << EOF
	cd Public/classes/
	get -r nohup.out Results/nohupOUT.out
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printLounge para a máquina ${bold}6${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws06.ua.pt << EOF
	cd Public/classes/
	get -r nohup.out Results/nohupLOUNGE.out
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printCustomer para a máquina ${bold}7${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws07.ua.pt << EOF
	cd Public/classes/
	get -r nohup.out Results/nohupCusto.out
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printMechanics para a máquina ${bold}8${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws10.ua.pt << EOF
			cd Public/classes/
			get -r nohup.out Results/nohupMech.out
			bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printManager para a máquina ${bold}9${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws09.ua.pt << EOF
	cd Public/classes/
	get -r nohup.out Results/nohupMang.out
	bye
EOF

bold=$(tput bold)
normal=$(tput sgr0)
echo "\n${bold}*** Script de Recolha do Log ***${normal}"

export SSHPASS='enterro2019'

###

echo -e "\n${bold}* Recolha do log no nó *${normal}"

echo -e "\n${bold}->${normal} A recolher o ficheiro de log da máquina ${bold}1${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws01.ua.pt << EOF
	cd Public/
	get -r printLogger.txt Results/printRegistry.txt
	get -r printLogger.txt Results/printLogger.txt
	get -r printServer.txt Results/printServer.txt
	cd dir_logger/classes/
	get -r log.txt Results/log.txt
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printRepairArea para a máquina ${bold}2${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws02.ua.pt << EOF
	get -r printRepairArea.txt Results/printRepairArea.txt
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printPark para a máquina ${bold}3${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws03.ua.pt << EOF
	get -r printPark.txt Results/printPark.txt
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printSupplierSite para a máquina ${bold}4${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws04.ua.pt << EOF
	get -r printSupplierSite.txt Results/printSupplierSite.txt
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printOutsideWorld para a máquina ${bold}5${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws05.ua.pt << EOF
	get -r printOutsideWorld.txt Results/printOutsideWorld.txt
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printLounge para a máquina ${bold}6${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws06.ua.pt << EOF
	get -r printLounge.txt Results/printLounge.txt
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printCustomer para a máquina ${bold}7${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws07.ua.pt << EOF
	get -r printCustomer.txt Results/printCustomer.txt
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printMechanics para a máquina ${bold}8${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws10.ua.pt << EOF
	get -r printMechanics.txt Results/printMechanics.txt
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printManager para a máquina ${bold}9${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws09.ua.pt << EOF
	get -r printManager.txt Results/printManager.txt
	bye
EOF

bold=$(tput bold)
normal=$(tput sgr0)
echo "${bold}*** Script de Deployment ***${normal}"

export SSHPASS='enterro2019'

echo -e "\n${bold}* Matar processos em cada nó *${normal}"

sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws01.ua.pt << EOF
	lsof -n -i:22410 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9
	lsof -n -i:22411 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9
	lsof -n -i:22412 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9
    exit
EOF

sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws02.ua.pt << EOF
	lsof -n -i:22413 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9
    exit
EOF

sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws03.ua.pt << EOF
	lsof -n -i:22414 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9
    exit
EOF

sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws04.ua.pt << EOF
	lsof -n -i:22415 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9
    exit
EOF

sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws05.ua.pt << EOF
	lsof -n -i:22416 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9
    exit
EOF

sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws06.ua.pt << EOF
	lsof -n -i:22417 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9
    exit
EOF

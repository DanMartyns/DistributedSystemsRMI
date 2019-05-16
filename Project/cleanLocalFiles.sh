bold=$(tput bold)
normal=$(tput sgr0)
echo "${bold}*** Clean Local Files ***${normal}"

echo -e "\n${bold}* Limpeza dos ficheiros class em cada nó *${normal}\n"

echo -e "${bold}->${normal} A limpar o Manager"
cd Manager/
find . -name '*.class' -type f -delete
cd ..
echo -e "\n${bold}->${normal} A limpeza terminou"

textfilelist=[]
import glob, os
path="C:\\Users\\Yatish H R\\Desktop\\SqlController"
os.chdir(path)
for file in glob.glob("*.txt"):
    if(file!="hypernyms.txt" and file!="synsets.txt" and file!="tempy.txt"):
        print(file)
        textfilelist.append(file.split('.')[0])

with open("tempy.txt","w") as fh1:
    for i in textfilelist:
        with open(i+".txt","r") as fh:
            basestring="<"+i+">\n"
            base=""
            for j in fh.readlines():
                base+="    <item>"+j[:len(j)-1]+ "</item>\n"
        basestring=basestring+base+"</"+i+">\n"
        fh.close()
        fh1.writelines(basestring)
fh1.close()
        
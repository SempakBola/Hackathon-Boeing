import json
import sqlite3
import os
  
# Folder Path
path = "../JSON"
  
# Change the directory
os.chdir(path)

file_list = []

def read_text_file(file_path):
    with open(file_path, 'r') as f:
        print(f.read())
  
  
# iterate through all file
for file in os.listdir():
    # Check whether file is in text format or not
    if file.endswith(".json"):
        file_path = f"{path}/{file}"
        file_list.append(file_path)
        # print(file_path)

conn = sqlite3.connect('../Hackathon-Boeing/database/Hackathon.db')
part = []
part_values = []
   
for filename in file_list:

    # filename = '..file_path'
    with open(filename) as json_file:
        json_data = json.load(json_file)



    details = json_data['RunDetails']

    # runtime_detail
    value = []
    values = []
    for data in json_data['PartInformation']:
        value.append(data['WorkOrder'])
        value.append(data['PartNumber'])
        value.append(data['ToolLocation'])
        value.append(data['Comment1'])
        value.append(data['Comment2'])
        value.append(data['Comment3'])
        value.append(str(data['PartTCs']))
        value.append(str(data['PartProbes']))
        values.append(list(value))
        value.clear()
    insert_query = 'insert into Part_Order values (?,?,?,?,?,?,?,?)'
    c = conn.cursor()
    c.executemany(insert_query, values)
    values.clear()




    # value = []
    # values = []

    # value.append(json_data['RunDetails']['ExportControl'])
    # values.append(list(value))
    # value.clear()


    # insert_query = 'insert into Export_Control values (?)'
    # c = conn.cursor()
    # c.executemany(insert_query, values)

    # file_info
    value = []
    values = []

    value.append(json_data['RunDetails']['LoadNumber'])
    value.append(json_data['RunDetails']['ExportControl'])
    value.append(json_data['RunDetails']['IP'])
    value.append(json_data['RunDetails']['FileName'])
    value.append(json_data['RunDetails']['FilePath'])
    values.append(list(value))
    value.clear()


    insert_query = 'insert into File_Info values (?,?,?,?,?)'


    c = conn.cursor()
    c.executemany(insert_query, values)
    values.clear()

    value = []
    values = []

    value.append(json_data['RunDetails']['OperatorName'])
    value.append(json_data['RunDetails']['LoadNumber'])
    value.append(json_data['RunDetails']['RunRecipe'])
    value.append(json_data['RunDetails']['RunStart'])
    value.append(json_data['RunDetails']['RunEnd'])
    value.append(json_data['RunDetails']['RunDuration'])
    value.append(json_data['RunDetails']['Equipment'])
    values.append(list(value))
    value.clear()


    insert_query = 'insert into Runtime_Detail values (?,?,?,?,?,?,?)'


    c = conn.cursor()
    c.executemany(insert_query, values)
    values.clear()



    value = []
    values = []

    
    for data in json_data['PartInformation']:
        value.append(json_data['RunDetails']['LoadNumber'])
        value.append(data['WorkOrder'])
        values.append(list(value))
        value.clear()


    insert_query = 'insert into Runtime_Part_Ralation values (?,?)'


    c = conn.cursor()
    c.executemany(insert_query, values)
    values.clear()

    for data in json_data['PartInformation']:
        part.append(data['PartNumber'])
        part.append(data['PartDescription'])
        # print(value)
        if list(part) not in part_values:
            part_values.append(list(part))
        part.clear()
    c.close()
    
insert_query = 'insert into Part_Info values (?,?)'
c = conn.cursor()
c.executemany(insert_query, part)
conn.commit()
c.close()
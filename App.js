
import React from 'react';
import 'antd/dist/antd.css';
import './index.css';
import { Layout, Menu, Icon, Button } from 'antd';
import { Upload, message } from 'antd';
import { CSVLink } from "react-csv";


const { Header, Sider, Content } = Layout;
const { Dragger } = Upload;

const axios = require('axios')

// const querystring = require('querystring');

const props = {
    name: 'file',
    multiple: false,
    action: 'https://www.mocky.io/v2/5cc8019d300000980a055e76',
    onChange(info) {
        const { status } = info.file;
        if (status !== 'uploading') {
            console.log(info.file, info.fileList);


        }
        if (status === 'done') {
            message.success(`${info.file.name} file uploaded successfully.`);

            let formData = new FormData();
            formData.append('file', info.file);
            console.log('>> formData >> ', formData);

            let axiosConfig = {
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8',
                    "Access-Control-Allow-Origin": "*",
                }
              };
          
          
            axios({url: 'http://localhost:8080/upload',
                   method: "POST",
                   data: formData,
                   headers:{'Accept': 'application/json','Content-Type': 'multipart/form-data;boundary=----WebKitFormBoundaryqRAOAKBFnLGNQtg0'},
                   
            }).catch(function (error) {
                // handle error
                console.log(error);
              })

            // axios.post('http://localhost:8080/upload',
            //     formData.file,
            //     { headers: { 'Content-Type': 'multipart/form-data;'}}

            // );

            // fetch("http://localhost:8080/upload",
            //     {
            //         headers: {
            //             // 'content-type': 'multipart/form-data; boundary=${formData._boundary}',
            //             'Content-Type': 'multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW',
            //           },
            //         body: formData.file,
            //         method: "post"
            //     });



        } else if (status === 'error') {
            message.error(`${info.file.name} file upload failed.`);
        }
    },
};

const csvData = [
    ["firstname", "lastname", "email"],
    ["Ahmed", "Tomi", "ah@smthing.co.com"],
    ["Raed", "Labes", "rl@smthing.co.com"],
    ["Yezzi", "Min l3b", "ymin@cocococo.com"]
];

const prettyLink = {
    backgroundColor: 'blue',
    fontSize: 14,
    fontWeight: 500,
    height: 600,
    padding: '0 25px',
    borderRadius: 5,
    color: '#fff'
};



class App1 extends React.Component {
    state = {
        collapsed: false,
    };

    toggle = () => {
        this.setState({
            collapsed: !this.state.collapsed,
        });
    };

    handleSubmit = () => {
        message.success('Your CSV has been submitted to process in the backend');


        axios.get('http://localhost:8080/greeting', {
            params: {
                name: 'feihong xu'
            }
        })
            .then(function (response) {
                console.log('this is success request');
                message.success('Your request has been proceed successfully');
            })
            .catch(function (error) {
                console.log(error);
                message.success('There is issue on your request');
            })
            .then(function () {
                // always executed
            });



        // axios.get('http://localhost:8080/greeting', {
        //     validateStatus: function (status) {
        //         console.log('status code = ', status)
        //     }
        // });

    }

    handleDownload = () => {
        // let url = window.URL.createObjectURL(blob);
        let url = 'www.host.com'
        let a = document.createElement('a');
        a.href = url;
        //a.download = filename;
        a.click();

        message.success('Will download the linkage result');
    }

    render() {
        return (
            <Layout>

                <Sider trigger={null} collapsible collapsed={this.state.collapsed}>
                    <div className="logo" />
                    <Menu theme="dark" mode="inline" defaultSelectedKeys={['1']}>
                        <Menu.Item key="1">
                            <span>SCI Linkage</span>
                        </Menu.Item>
                        <Menu.Item key="2">
                            <span>Name Dedup</span>
                        </Menu.Item>
                    </Menu>
                </Sider>
                <Layout>
                    <Header style={{ background: '#fff', padding: 0 }}>
                        <Icon
                            className="trigger"
                            type={this.state.collapsed ? 'menu-unfold' : 'menu-fold'}
                            onClick={this.toggle}
                        />
                    </Header>
                    <Content
                        style={{
                            margin: '24px 16px',
                            padding: 24,
                            background: '#fff',
                            minHeight: 800,
                        }}
                    >
                        Please upload the csv file for SCI linkage:
                        <Dragger {...props}>
                            <p className="ant-upload-drag-icon">
                                <Icon type="inbox" />
                            </p>
                            <p className="ant-upload-text">Click or drag file to this area to upload</p>
                            <p className="ant-upload-hint">
                                Upload csv file should have column company_name, company_country, company_address in sequence
                             </p>
                        </Dragger>
                        <br></br>

                        <div style={{
                            position: 'absolute', left: '50%', top: '45%',
                            transform: 'translate(-50%, -50%)'
                        }}  >
                            <Button type="primary" onClick={this.handleSubmit} >Submit</Button>

                            {/* <Button type="primary" icon="download" onClick={this.handleDownload} >Download Result</Button> */}
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <CSVLink data={csvData} filename={"entity_resolution.csv"} style={prettyLink} >Download me</CSVLink>

                        </div>
                    </Content>
                </Layout>
            </Layout>
        );
    }
}

export default App1;

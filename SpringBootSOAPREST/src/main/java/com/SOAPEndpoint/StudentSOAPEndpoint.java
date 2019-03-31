package com.SOAPEndpoint;


import com.Repository.StudentRepository;


import com.com.example.student.GetStudentByIDRequest;
import com.com.example.student.GetStudentByIDResponse;
import com.com.example.student.UpdateStudentByIDRequest;
import com.com.example.student.UpdateStudentByIDResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class StudentSOAPEndpoint {
    private static final String NAMESPACE_URI = "http://example.com/student";

    private StudentRepository studentRepository;

    @Autowired
    public StudentSOAPEndpoint(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentByIDRequest")
    @ResponsePayload
    public GetStudentByIDResponse getStudent(@RequestPayload GetStudentByIDRequest request) {
        GetStudentByIDResponse response = new GetStudentByIDResponse();
        response.setStudent(studentRepository.findStudentByID(request.getId()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateStudentByIDRequest")
    @ResponsePayload
    public UpdateStudentByIDResponse updateStudent(@RequestPayload UpdateStudentByIDRequest request) {

        studentRepository.updateStudentByID(request.getId(), request.getStudent());
        UpdateStudentByIDResponse response = new UpdateStudentByIDResponse();
        response.setUPDATED("UPDATED");
        return response;
    }
}
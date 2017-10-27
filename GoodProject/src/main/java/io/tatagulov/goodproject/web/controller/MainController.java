package io.tatagulov.goodproject.web.controller;


import io.tatagulov.goodproject.web.api.MapParamProvider;
import io.tatagulov.goodproject.web.api.ParamProvider;
import io.tatagulov.goodproject.web.api.WebContext;
import io.tatagulov.goodproject.web.api.dto.TableData;
import io.tatagulov.goodproject.web.api.dto.TableDataRequest;
import io.tatagulov.goodproject.web.api.dto.UpdateRequest;
import io.tatagulov.goodproject.web.api.repo.*;
import io.tatagulov.goodproject.web.service.JDBCHelper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.Map;

@RestController
@Transactional
public class MainController {

    private final ApplicationContext applicationContext;
    private final JDBCHelper jdbcHelper;

    @Autowired
    public MainController(ApplicationContext applicationContext, JDBCHelper jdbcHelper) {
        this.applicationContext = applicationContext;
        this.jdbcHelper = jdbcHelper;
    }

    @RequestMapping("/tableData/{repoName}")
    public TableData tableData(@PathVariable String repoName,
                               @RequestBody TableDataRequest request,
                               Locale locale) {
        WebContext context = new WebContext(locale);
        ParamProvider paramProvider = new MapParamProvider(request.filter);
        try {
            TableDataRepository tableDataRepository = applicationContext.getBean(repoName,TableDataRepository.class);
            return tableDataRepository.getTableData(paramProvider, request.page, request.pageSize, context);
        } catch (BeansException e) {
            SelectTableData repository = (SelectTableData) applicationContext.getBean(repoName);
            return jdbcHelper.getTableData(paramProvider, request.page, request.pageSize, repository, context);
        }
    }

    @RequestMapping("/selectOne/{repoName}")
    public Map<String, String> selectOne(@PathVariable String repoName,
                                         @RequestBody Map<String, String> keyParams,
                                         Locale locale) {
        SelectOneRepository selectOneRepository = (SelectOneRepository) applicationContext.getBean(repoName);
        return selectOneRepository.selectOne(new MapParamProvider(keyParams), new WebContext(locale));
    }

    @RequestMapping("/insert/{repoName}")
    public Map<String, String> insert(@PathVariable String repoName,
                                      @RequestBody Map<String, String> insertParams,
                                      Locale locale) {
        InsertRepository insertRepository = (InsertRepository) applicationContext.getBean(repoName);
        insertRepository.insert(new MapParamProvider(insertParams), new WebContext(locale));
        return insertParams;
    }

    @RequestMapping("/update/{repoName}")
    public Map<String, String> update(@PathVariable String repoName,
                                      @RequestBody UpdateRequest updateRequest,
                                      Locale locale) {
        UpdateRepository updateRepository = (UpdateRepository) applicationContext.getBean(repoName);
        updateRepository.update(new MapParamProvider(updateRequest.keyParam), new MapParamProvider(updateRequest.updateParams), new WebContext(locale));
        return updateRequest.updateParams;
    }

    @RequestMapping("/delete/{repoName}")
    public void delete(@PathVariable String repoName,
                       @RequestBody Map<String, String> params,
                       Locale locale) {
        DeleteRepository deleteRepository = (DeleteRepository) applicationContext.getBean(repoName);
        deleteRepository.delete(new MapParamProvider(params), new WebContext(locale));
    }
}

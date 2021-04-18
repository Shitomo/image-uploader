WITH answer_summary AS (
    SELECT
        survey_id AS answer_survey_id,
        target_id,
        target_children_id
    FROM answers
    WHERE
            survey_id = 1 AND target_id = 1
    GROUP BY
        survey_id,target_id,target_children_id
),
     target_summary AS (
         SELECT
             survey_id AS target_survey_id,
             parent_id,
             children_id
         FROM targets
         WHERE
                 survey_id = 1 AND parent_id = 1
         GROUP BY
             survey_id,parent_id,children_id
     ),
     answer_sum_summary AS (
         SELECT
             answer_survey_id,
             target_id,
             COUNT(*) AS answer_sum
         FROM answer_summary
         GROUP BY
             answer_survey_id,target_id
     ),
     target_sum_summary AS (
         SELECT
             target_survey_id,
             parent_id,
             COUNT(*) AS target_sum
         FROM target_summary
         GROUP BY
             target_survey_id,parent_id
     )
SELECT
    answer_sum_summary.answer_sum,
    target_sum_summary.target_sum
FROM
    answer_sum_summary, target_sum_summary